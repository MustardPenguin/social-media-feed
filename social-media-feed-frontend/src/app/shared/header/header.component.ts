import { Component } from '@angular/core';
import { MatButtonModule, } from '@angular/material/button';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [MatButtonModule, RouterModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  constructor(private router: Router) { }

  navigateTo(url: string): void {
    console.log(url);
    this.router.navigate([url]);
  }
}
