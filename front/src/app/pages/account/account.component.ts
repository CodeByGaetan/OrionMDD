import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject, Observable, Subscription, combineLatest, map, take, tap } from 'rxjs';
import { UserRequest } from 'src/app/interfaces/requests/userRequest.interface';
import { Topic } from 'src/app/interfaces/topic.interface';
import { TopicService } from 'src/app/services/topic.service';
import { UserService } from 'src/app/services/user.service';

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

  public subTopicIds: number[] = [];
  public topics$!: Observable<Topic[]>;

  public onError = false;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private topicService: TopicService
  ) { }

  ngOnInit(): void {
    this.topics$ = this.topicService.getAll();

    this.userService.getUserInfo().pipe(take(1)).subscribe(userInfo => {
      this.userForm.patchValue({
        name: userInfo.name,
        email: userInfo.email
      });
      this.subTopicIds = userInfo.topicIds;
    });
  }

  updateUser(): void {
    const userRequest = this.userForm.value as UserRequest;
    this.userService.updateUser(userRequest).pipe(take(1)).subscribe({
      next: (_: void) => { },
      error: () => this.onError = true
    });
  }

  unSubscribe(topicId: number) {
    this.userService.unSubscribeTopic(topicId).pipe(take(1)).subscribe({
      next: (topicIds) => {
        this.subTopicIds = topicIds;
      },
      error: () => { }
    });
  }

}
