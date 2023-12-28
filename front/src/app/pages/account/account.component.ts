import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, tap } from 'rxjs';
import { Topic } from 'src/app/interfaces/topic.interface';
import { User } from 'src/app/interfaces/user.interface';
import { TopicService } from 'src/app/services/topic.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {

  public userForm!: FormGroup;
  public topics$!: Observable<Topic[]>;
  // public user$!: Observable<User>

  public onError = false;

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private topicService: TopicService
  ) { }

  ngOnInit(): void {

    this.userService.getUserInfo().pipe(
      tap(userInfo => {
        this.userForm = this.formBuilder.group({
          name: [
            userInfo.name,
            [
              Validators.required,
              Validators.min(3),
              Validators.max(20)
            ]
          ],
          email: [
            userInfo.email,
            [
              Validators.required,
              Validators.email
            ]
          ]
        });
      })
    ).subscribe();

    this.topics$ = this.topicService.getAll();
    
  }

  updateUser(): void {

  }

}
