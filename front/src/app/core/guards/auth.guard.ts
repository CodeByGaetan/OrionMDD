import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { SessionService } from '../services/session.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private sessionService: SessionService,
  ) { }

  public canActivate(): boolean {
    if (!this.sessionService.isLogged) {
      this.router.navigateByUrl('/home');
      return false;
    }
    return true;
  }

}
