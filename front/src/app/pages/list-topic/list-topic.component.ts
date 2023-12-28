import { Component, OnDestroy, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, Subject, take, takeUntil, tap } from 'rxjs';
import { Topic } from 'src/app/interfaces/topic.interface';
import { TopicService } from 'src/app/services/topic.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-list-topic',
  templateUrl: './list-topic.component.html',
  styleUrls: ['./list-topic.component.scss']
})
export class ListTopicComponent implements OnInit, OnDestroy {

  public topics$!: Observable<Topic[]>;
  public topicIds: number[] = [];
  
  private destroy$!: Subject<boolean>;

  constructor(
    private topicService: TopicService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.destroy$ = new Subject<boolean>();
    
    this.topics$ = this.topicService.getAll();
    this.userService.getTopicIds().pipe(
      takeUntil(this.destroy$),
      tap(ids => this.topicIds = ids)
    ).subscribe();
  }

  subscribe(topicId: number): void {
    this.userService.subscribeTopic(topicId).subscribe({
      next: (_: void) => {
        this.topicIds.push(topicId);      
      },
      error: () => {}
    });
  }

  unSubscribe(topicId: number): void {
    this.userService.unSubscribeTopic(topicId).subscribe({
      next: (_: void) => {
        this.topicIds.splice(this.topicIds.indexOf(topicId), 1);
      },
      error: () => {}
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
  }

}
