import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { SignUpRequest } from '../../interfaces/signUpRequest.interface';
import { HttpErrorResponse } from '@angular/common/http';

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

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  public onSubmit(): void {
    const signUpRequest = this.signUpForm.value as SignUpRequest;
    this.authService.signUp(signUpRequest).subscribe({
      next: (_: void) => {
        this.router.navigateByUrl('/');
      },
      error: (error : HttpErrorResponse) => {
        this.codeError = error.error.codeError;
      }
    });
  }

}
