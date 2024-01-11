import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from "@angular/forms";
import { SignInRequest } from '../../interfaces/signInRequest.interface';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { SessionInformation } from '../../../core/interfaces/sessionInformation.interface';
import { SessionService } from '../../../core/services/session.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {

  public signInForm = this.formBuilder.group({
    username: [
      '',
      [
        Validators.required
      ]
    ],
    password: [
      '',
      [
        Validators.required,
        Validators.min(8)
      ]
    ]
  });

  public codeError = 0;
  public generalError = false;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private sessionService: SessionService,
    private router: Router
  ) { }

  public onSubmit(): void {
    const signInRequest = this.signInForm.value as SignInRequest;
    this.authService.signIn(signInRequest).subscribe({
      next: (response) => {
        this.sessionService.logIn(response);
        this.router.navigateByUrl('/');
      },
      error: (error: HttpErrorResponse) => {
        if (error.error.codeError) {
          this.codeError = error.error.codeError;
        } else {
          this.generalError = true;
        }
      }
    });
  }
}
