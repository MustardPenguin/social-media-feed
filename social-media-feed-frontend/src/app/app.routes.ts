import { Routes } from '@angular/router';
import { RegisterComponent } from './features/register/register.component';
import { LoginComponent } from './features/login/login.component';
import { HomeComponent } from './features/home/home.component';
import { FollowComponent } from './features/follow/follow.component';
import { PostComponent } from './features/post/post.component';
import { FeedComponent } from './features/feed/feed.component';

export const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent},
  { path: 'home', component: HomeComponent},
  { path: 'follows', component: FollowComponent },
  { path: 'post', component: PostComponent },
  { path: 'feed', component: FeedComponent },
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];
