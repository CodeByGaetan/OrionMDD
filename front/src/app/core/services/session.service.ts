import { Injectable } from '@angular/core';
import { SessionInformation } from '../interfaces/sessionInformation.interface';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public isLogged = false;
  public sessionInformation: SessionInformation | undefined;

  constructor(private router: Router) { }

  public logIn(sessionInformation: SessionInformation): void {
    this.sessionInformation = sessionInformation;
    this.isLogged = true;
  }

  public logOut(): void {
    this.sessionInformation = undefined;
    this.isLogged = false;
    this.router.navigateByUrl('/');
  }

}