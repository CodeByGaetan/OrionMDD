import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject, Observable, Subscription, combineLatest, map, take, tap } from 'rxjs';
import { UserRequest } from 'src/app/user/interfaces/userRequest.interface';
import { Topic } from 'src/app/topics/interfaces/topic.interface';
import { TopicService } from 'src/app/topics/services/topic.service';
import { UserService } from 'src/app/user/services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SessionService } from 'src/app/core/services/session.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from 'src/app/auth/services/auth.service';

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

  public codeError = 0;
  public generalErrorUser = false;
  public onErrorTopics = false;

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private topicService: TopicService,
    private sessionService: SessionService,
    private snackBar: MatSnackBar
  ) { }

  private fetchTopics(): void {
    this.topicService.getAll(true).pipe(take(1)).subscribe({
      next: (response) => {
        this.topics = response.topics;
      },
      error: () => this.onErrorTopics = true
    })
  }

  public ngOnInit(): void {
    this.userService.getUserInfo().pipe(take(1)).subscribe({
      next: (userInfo) => {
      this.userForm.patchValue({
        name: userInfo.name,
        email: userInfo.email
      })},
      error: () => this.generalErrorUser = true
  });

    this.fetchTopics();
  }

  public updateUser(): void {
    const userRequest = this.userForm.value as UserRequest;
    this.authService.updateUser(userRequest).pipe(take(1)).subscribe({
      next: (response) => {
        if (response.token != null) {
          this.sessionService.logIn(response);
        }
        this.codeError = 0;
        this.generalErrorUser = false;
      },
      error: (error : HttpErrorResponse) => {
        if (error.error.codeError) {
          this.codeError = error.error.codeError;
        } else {
          this.generalErrorUser = true;
        }
      }
    });
  }

  public logOut(): void {
    this.sessionService.logOut();
  }

  public unSubscribe(topicId: number) {
    this.userService.unSubscribeTopic(topicId).pipe(take(1)).subscribe( {
      next: () => this.fetchTopics(),
      error: () => this.snackBar.open("Erreur lors du désabonnement", "Fermer", {
        panelClass: 'error-snackbar'
      })
    });
  }

}
