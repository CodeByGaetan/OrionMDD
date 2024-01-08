import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject, Observable, Subscription, combineLatest, map, take, tap } from 'rxjs';
import { UserRequest } from 'src/app/user/interfaces/userRequest.interface';
import { Topic } from 'src/app/topics/interfaces/topic.interface';
import { TopicService } from 'src/app/topics/services/topic.service';
import { UserService } from 'src/app/user/services/user.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  public userForm = this.formBuilder.group({
    name: [
      '',
      [
        Validators.required,
        Validators.min(3),
        Validators.max(20)
      ]
    ],
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ]
  });;

  public topics!: Topic[];

  public onError = false;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private topicService: TopicService
  ) { }

  private fetchTopics(): void {
    this.topicService.getAll(true).pipe(take(1)).subscribe({
      next: (response) => {
        this.topics = response.topics;
      }
    })
  }

  public ngOnInit(): void {
    this.userService.getUserInfo().pipe(take(1)).subscribe(userInfo => {
      this.userForm.patchValue({
        name: userInfo.name,
        email: userInfo.email
      });
    });

    this.fetchTopics();
  }

  public updateUser(): void {
    const userRequest = this.userForm.value as UserRequest;
    this.userService.updateUser(userRequest).pipe(take(1)).subscribe({
      next: (_: void) => { },
      error: () => this.onError = true
    });
  }

  public unSubscribe(topicId: number) {
    this.userService.unSubscribeTopic(topicId).pipe(take(1)).subscribe(() => {
      this.fetchTopics();
    });
  }

}
