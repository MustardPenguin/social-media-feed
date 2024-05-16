import { Component, inject } from '@angular/core';
import FieldType from '../../shared/interfaces/FieldType';
import { AuthenticationService } from '../../core/authentication.service';
import { FormService } from '../../core/form.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  fields: FieldType[] = [
    { name: "username", type: "text" },
    { name: "password", type: "password" }
  ];

  constructor(private authenticationService: AuthenticationService, private formService: FormService) {}

  ngOnInit(): void {
    const element: Element | null = document.querySelector('.form');
    this.formService.createForm(element, this.fields);
  }

  onSubmit(): void {
    this.formService.getFormData(this.fields);
    this.authenticationService.registerAccount();
  }
}
