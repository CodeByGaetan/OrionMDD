import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { SessionService } from '../services/session.service';

@Injectable({
  providedIn: 'root'
})
export class UnauthGuard implements CanActivate {

  constructor(
    private router: Router,
    private sessionService: SessionService,
  ) { }

  public canActivate(): boolean {
    let token = this.sessionService.getJwtToken();
    if(token) {
      this.router.navigateByUrl('/');
      return false;
    }

    return true;
  }

}
