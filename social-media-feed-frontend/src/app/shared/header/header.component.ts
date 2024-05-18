import { Component } from '@angular/core';
import { MatButtonModule, } from '@angular/material/button';
import { Router, RouterModule } from '@angular/router';
import { AuthenticationService } from '../../core/authentication.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MatButtonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  authenticated: boolean = false;

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
    this.authenticated = this.authenticationService.isAuthenticated();
  }

  isAuthenticated(): boolean {
    return this.authenticationService.authenticated;
  }

  logout(): void {
    this.authenticationService.logout();
  }

  navigateTo(url: string): void {
    console.log(url);
    this.router.navigate([url]);
  }
}
