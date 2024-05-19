import { Routes } from '@angular/router';
import { RegisterComponent } from './features/register/register.component';
import { LoginComponent } from './features/login/login.component';
import { HomeComponent } from './features/home/home.component';
import { FollowComponent } from './features/follow/follow.component';

export const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent},
  { path: 'home', component: HomeComponent},
  { path: 'follows', component: FollowComponent },
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];