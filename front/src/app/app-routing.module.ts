import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './auth/components/home/home.component';
import { SignInComponent } from './auth/components/sign-in/sign-in.component';
import { SignUpComponent } from './auth/components/sign-up/sign-up.component';
import { ListPostComponent } from './posts/components/list-post/list-post.component';
import { ListTopicComponent } from './topics/components/list-topic/list-topic.component';
import { AccountComponent } from './user/components/account/account.component';
import { DetailPostComponent } from './posts/components/detail-post/detail-post.component';
import { NewPostComponent } from './posts/components/new-post/new-post.component';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: 'signin', component: SignInComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'posts', component: ListPostComponent },
  { path: 'topics', component: ListTopicComponent },
  { path: 'account', component: AccountComponent },
  { path: 'posts/create', component: NewPostComponent },
  { path: 'posts/:id', component: DetailPostComponent },
  { path: '', component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
