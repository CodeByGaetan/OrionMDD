import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from "@angular/forms";
import { SignInRequest } from '../../interfaces/signInRequest.interface';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {

  public signInForm = this.formBuilder.group({
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

  public onError = false;
  public errorMessage = "";

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  public onSubmit(): void {
    const signInRequest = this.signInForm.value as SignInRequest;
    this.authService.signIn(signInRequest).subscribe({
      next: (_: void) => {
        this.router.navigateByUrl('/');
      },
      error: (error: HttpErrorResponse) => {
        this.onError = true;
        this.errorMessage = error.error.message;
      }
    });
  }
}
