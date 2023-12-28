import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Topic } from 'src/app/interfaces/topic.interface';
import { TopicService } from 'src/app/services/topic.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-list-topic',
  templateUrl: './list-topic.component.html',
  styleUrls: ['./list-topic.component.scss']
})
export class ListTopicComponent implements OnInit {

  public topics$!: Observable<Topic[]>;
  public topicIds$!: Observable<number[]>;

  constructor(
    private topicService: TopicService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.topics$ = this.topicService.getAll();
    
    this.fetchTopicIds();
  }

  fetchTopicIds(): void {
    this.topicIds$ = this.userService.getTopicIds();
  }

  subscribe(topicId: number): void {
    this.userService.subscribeTopic(topicId).subscribe({
      next: (_: void) => {
        this.fetchTopicIds();       
      },
      error: () => {}
    });
  }

  unSubscribe(topicId: number): void {
    this.userService.unSubscribeTopic(topicId).subscribe({
      next: (_: void) => {
        this.fetchTopicIds();
      },
      error: () => {}
    });
  }

}
