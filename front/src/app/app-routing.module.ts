import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { SignUpComponent } from './pages/sign-up/sign-up.component';
import { ListPostComponent } from './pages/list-post/list-post.component';
import { ListTopicComponent } from './pages/list-topic/list-topic.component';
import { AccountComponent } from './pages/account/account.component';
import { DetailPostComponent } from './pages/detail-post/detail-post.component';
import { NewPostComponent } from './pages/new-post/new-post.component';

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
