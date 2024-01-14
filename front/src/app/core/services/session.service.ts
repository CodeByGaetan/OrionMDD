import { Injectable } from '@angular/core';
import { SessionInformation } from '../interfaces/sessionInformation.interface';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private router: Router) { }

  public getJwtToken(): String | null {
    return localStorage.getItem('access_token');
  }

  public logIn(sessionInformation: SessionInformation): void {
    localStorage.setItem('access_token', sessionInformation.token);
  }

  public logOut(): void {
    localStorage.clear();
    this.router.navigateByUrl('/');
  }

}