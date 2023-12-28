import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SignInRequest } from '../interfaces/requests/signInRequest.interface';
import { Observable } from 'rxjs';
import { SignUpRequest } from '../interfaces/requests/signUpRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private pathService = 'api/auth';

  constructor(private httpClient: HttpClient) { }

  public signIn(signInRequest: SignInRequest) : Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/signin`, signInRequest);
  }

  public signUp(signUpRequest: SignUpRequest) : Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/signup`, signUpRequest);
  }


}
