import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatDividerModule } from '@angular/material/divider';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { LayoutModule } from '@angular/cdk/layout';
import { MatListModule } from '@angular/material/list';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBarModule } from '@angular/material/snack-bar';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './auth/components/home/home.component';
import { SignInComponent } from './auth/components/sign-in/sign-in.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { SignUpComponent } from './auth/components/sign-up/sign-up.component';
import { ListPostComponent } from './posts/components/list-post/list-post.component';
import { ListTopicComponent } from './topics/components/list-topic/list-topic.component';
import { AccountComponent } from './user/components/account/account.component';
import { DetailPostComponent } from './posts/components/detail-post/detail-post.component';
import { NewPostComponent } from './posts/components/new-post/new-post.component';
import { PaginationComponent } from './shared/components/pagination/pagination.component';
import { NavigationBarComponent } from './core/components/navigation-bar/navigation-bar.component';
import { SpinnerComponent } from './core/components/spinner/spinner.component';
import { LoaderInterceptor } from './core/interceptors/loader.interceptor';
import { NotFoundComponent } from './core/components/not-found/not-found.component';


const materialModules = [
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatToolbarModule,
  MatDividerModule,
  MatSelectModule,
  MatSidenavModule,
  LayoutModule,
  MatListModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSnackBarModule
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SignInComponent,
    SignUpComponent,
    ListPostComponent,
    ListTopicComponent,
    AccountComponent,
    DetailPostComponent,
    NewPostComponent,
    NavigationBarComponent,
    PaginationComponent,
    SpinnerComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ...materialModules
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: LoaderInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent],
})
export class AppModule { }
