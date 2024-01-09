import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { take } from 'rxjs';
import { Topic } from 'src/app/topics/interfaces/topic.interface';
import { TopicService } from 'src/app/topics/services/topic.service';
import { UserService } from 'src/app/user/services/user.service';

@Component({
  selector: 'app-list-topic',
  templateUrl: './list-topic.component.html',
  styleUrls: ['./list-topic.component.scss']
})
export class ListTopicComponent implements OnInit {

  public subTopicIds!: number[];
  public topics!: Topic[];

  public onError = false;

  constructor(
    private topicService: TopicService,
    private userService: UserService,
    private snackBar: MatSnackBar
  ) { }

  private fetchTopics(): void {
    this.topicService.getAll(false).pipe(take(1)).subscribe({
      next: (response) => {
        this.topics = response.topics;
        this.subTopicIds = response.subTopicIds
      },
      error: () => this.onError = true
    })
  }

  public ngOnInit(): void {
    this.fetchTopics();
  }

  public subscribe(topicId: number): void {
    this.userService.subscribeTopic(topicId).pipe(take(1)).subscribe({
      next: (topicIds) => {
        this.subTopicIds = topicIds;
      },
      error: () => this.snackBar.open("Erreur lors de l'abonnement", "Fermer", {
        panelClass: 'error-snackbar'
      })
    });
  }

  public unSubscribe(topicId: number): void {
    this.userService.unSubscribeTopic(topicId).pipe(take(1)).subscribe({
      next: (topicIds) => {
        this.subTopicIds = topicIds;
      },
      error: () => this.snackBar.open("Erreur lors du désabonnement", "Fermer", {
        panelClass: 'error-snackbar'
      })
    });
  }

}
