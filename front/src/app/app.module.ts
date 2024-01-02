import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './auth/components/home/home.component';
import { SignInComponent } from './auth/components/sign-in/sign-in.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { SignUpComponent } from './auth/components/sign-up/sign-up.component';
import { ListPostComponent } from './posts/components/list-post/list-post.component';
import { ListTopicComponent } from './topics/components/list-topic/list-topic.component';
import { AccountComponent } from './user/components/account/account.component';
import { DetailPostComponent } from './posts/components/detail-post/detail-post.component';
import { NewPostComponent } from './posts/components/new-post/new-post.component';
import { ToolbarComponent } from './shared/toolbar/toolbar.component';

import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field'
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar'
import { MatDividerModule } from '@angular/material/divider';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatListModule } from '@angular/material/list';

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
  MatListModule
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
    ToolbarComponent,
    NavigationBarComponent
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
