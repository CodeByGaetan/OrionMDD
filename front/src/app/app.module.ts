import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SignUpComponent } from './pages/sign-up/sign-up.component';
import { ListPostComponent } from './pages/list-post/list-post.component';
import { ListTopicComponent } from './pages/list-topic/list-topic.component';
import { AccountComponent } from './pages/account/account.component';
import { DetailPostComponent } from './pages/detail-post/detail-post.component';
import { NewPostComponent } from './pages/new-post/new-post.component';

import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatDividerModule } from '@angular/material/divider';
import { MatSelectModule } from '@angular/material/select';

const materialModules = [
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatIconModule,
  MatToolbarModule,
  MatDividerModule,
  MatSelectModule,
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SignInComponent, SignUpComponent, ListPostComponent, ListTopicComponent, AccountComponent, DetailPostComponent, NewPostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ...materialModules
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
