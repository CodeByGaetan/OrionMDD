import { Component, ElementRef, HostListener, OnInit, ViewChild } from '@angular/core';
import { MatSidenav, MatSidenavContainer } from '@angular/material/sidenav';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.scss']
})
export class NavigationBarComponent implements OnInit {

  public screenWidth!: number;
  @ViewChild('test') test!: MatSidenavContainer;
  @ViewChild('drawer') drawer!: MatSidenav;

  constructor(public router: Router) { }

  ngOnInit() {
    this.screenWidth = window.innerWidth;
    // this.test.end = this.drawer;
  }

  @HostListener('window:resize', ['$event']) 
  public getScreenSize(event: Event) {
    this.screenWidth = window.innerWidth;
  }

  showToolbar(): boolean {
    const cond1 = this.router.url === '/';
    const cond2 = this.router.url === '/signin' && this.screenWidth < 600;
    const cond3 = this.router.url === '/signup' && this.screenWidth < 600;
    if (cond1 || cond2 || cond3) {
      return false;
    }
    return true;
  }

  showNavLinks(): boolean {
    return this.router.url !== '/signin' && this.router.url !== '/signup';
  }

}