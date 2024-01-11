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
import { NotFoundComponent } from './core/components/not-found/not-found.component';
import { AuthGuard } from './core/guards/auth.guard';
import { UnauthGuard } from './core/guards/unauth.guard';
import { ExpiredComponent } from './core/components/expired/expired.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent, canActivate: [UnauthGuard] },
  { path: 'signin', component: SignInComponent, canActivate: [UnauthGuard] },
  { path: 'signup', component: SignUpComponent, canActivate: [UnauthGuard] },
  { path: 'posts', component: ListPostComponent, canActivate: [AuthGuard] },
  { path: 'topics', component: ListTopicComponent, canActivate: [AuthGuard] },
  { path: 'account', component: AccountComponent, canActivate: [AuthGuard] },
  { path: 'posts/create', component: NewPostComponent, canActivate: [AuthGuard] },
  { path: 'posts/:id', component: DetailPostComponent, canActivate: [AuthGuard] },
  { path: 'not-found', component: NotFoundComponent, canActivate: [AuthGuard] },
  { path: 'expired', component: ExpiredComponent, canActivate: [UnauthGuard] },
  { path: '', redirectTo: '/posts', pathMatch: 'full' },
  { path: '**', redirectTo: '/not-found', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule],
})
export class AppRoutingModule { }
