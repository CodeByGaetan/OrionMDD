import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { SignUpRequest } from '../../interfaces/requests/signUpRequest.interface';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {

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

  public onError = false;
  public errorMessage = "";

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const signUpRequest = this.signUpForm.value as SignUpRequest;
    this.authService.signUp(signUpRequest).subscribe({
      next: (_: void) => {
        this.router.navigateByUrl('/');
      },
      error: (error : HttpErrorResponse) => {
        this.onError = true;
        this.errorMessage = error.error.message;
      }
    });
  }

}
