import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { SignUpRequest } from '../../interfaces/signUpRequest.interface';
import { HttpErrorResponse } from '@angular/common/http';
import { SessionService } from '../../../core/services/session.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {

  public signUpForm = this.formBuilder.group({
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
    const signUpRequest = this.signUpForm.value as SignUpRequest;
    this.authService.signUp(signUpRequest).subscribe({
      next: (response) => {
        this.sessionService.logIn(response);
        this.router.navigateByUrl('/');
      },
      error: (error : HttpErrorResponse) => {
        if (error.error.codeError) {
          this.codeError = error.error.codeError;
        } else {
          this.generalError = true;
        }
      }
    });
  }

}
