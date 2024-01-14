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
    const withoutToolbar = ['/home', '/not-found', '/expired'];
    const screenCondition = ['/signin', '/signup'];

    const cond1 = withoutToolbar.includes(this.router.url);
    const cond2 = screenCondition.includes(this.router.url) && this.screenWidth < 600;
    if (cond1 || cond2) {
      return false;
    }
    return true;
  }

  public showNavLinks(): boolean {
    return this.router.url !== '/signin' && this.router.url !== '/signup';
  }

}