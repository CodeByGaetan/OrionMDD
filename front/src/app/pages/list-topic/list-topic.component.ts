import { Component, OnDestroy, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, Subject, Subscription, take, takeUntil, tap } from 'rxjs';
import { Topic } from 'src/app/interfaces/topic.interface';
import { TopicService } from 'src/app/services/topic.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-list-topic',
  templateUrl: './list-topic.component.html',
  styleUrls: ['./list-topic.component.scss']
})
export class ListTopicComponent implements OnInit {

  public subTopicIds: number[] = [];
  public topics$!: Observable<Topic[]>;

  constructor(
    private topicService: TopicService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.topics$ = this.topicService.getAll();
    this.userService.getTopicIds().pipe(take(1)).subscribe(ids => this.subTopicIds = ids);
  }

  subscribe(topicId: number): void {
    this.userService.subscribeTopic(topicId).pipe(take(1)).subscribe({
      next: (topicIds) => {
        this.subTopicIds = topicIds;
      },
      error: () => { }
    });
  }

  unSubscribe(topicId: number): void {
    this.userService.unSubscribeTopic(topicId).pipe(take(1)).subscribe({
      next: (topicIds) => {
        this.subTopicIds = topicIds;
      },
      error: () => { }
    });
  }

}
