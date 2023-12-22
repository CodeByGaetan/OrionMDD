import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SignInRequest } from '../interfaces/signInRequest.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private pathService = 'http://localhost:44001/api/auth';

  constructor(private httpClient: HttpClient) { }

  public signIn(signInRequest: SignInRequest) : Observable<void> {
    return this.httpClient.post<void>(`${this.pathService}/signin`, signInRequest);
  }
}
