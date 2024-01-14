import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SignInRequest } from '../interfaces/signInRequest.interface';
import { Observable } from 'rxjs';
import { SignUpRequest } from '../interfaces/signUpRequest.interface';
import { SessionInformation } from '../../core/interfaces/sessionInformation.interface';
import { UserRequest } from 'src/app/user/interfaces/userRequest.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private pathService = 'api/auth';

  constructor(private httpClient: HttpClient) { }

  public signIn(signInRequest: SignInRequest) : Observable<SessionInformation> {
    return this.httpClient.post<SessionInformation>(`${this.pathService}/signin`, signInRequest);
  }

  public signUp(signUpRequest: SignUpRequest) : Observable<SessionInformation> {
    return this.httpClient.post<SessionInformation>(`${this.pathService}/signup`, signUpRequest);
  }

  public updateUser(userRequest: UserRequest): Observable<SessionInformation> {
    return this.httpClient.put<SessionInformation>(`${this.pathService}/user`, userRequest);
  }

}
