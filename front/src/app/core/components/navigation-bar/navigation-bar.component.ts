import { Component, HostListener, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss']
})
export class NavigationBarComponent implements OnInit {

  public screenWidth!: number;

  constructor(public router: Router) { }

  public ngOnInit() {
    this.screenWidth = window.innerWidth;
  }

  @HostListener('window:resize', ['$event']) 
  public getScreenSize(event: Event) {
    this.screenWidth = window.innerWidth;
  }

  public showToolbar(): boolean {
    const cond1 = this.router.url === '/home';
    const cond2 =  this.router.url === '/not-found';
    const cond3 = this.router.url === '/signin' && this.screenWidth < 600;
    const cond4 = this.router.url === '/signup' && this.screenWidth < 600;
    if (cond1 || cond2 || cond3 || cond4) {
      return false;
    }
    return true;
  }

  public showNavLinks(): boolean {
    return this.router.url !== '/signin' && this.router.url !== '/signup';
  }

}