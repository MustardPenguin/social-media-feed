import { Component, Input } from '@angular/core';

import { FormComponent } from '../../shared/form/form.component';
import { FormService } from '../../core/form.service';

import FieldType from '../../shared/interfaces/FieldType';
import FormData from '../../shared/interfaces/FormData';
import { Observable, Subscription } from 'rxjs';
import { AuthenticationService } from '../../core/authentication.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  fields: FieldType[] = [
    { name: "username", type: "text", icon: "person" },
    { name: "password", type: "password", icon: "password" }
  ];

  private connection: Subscription | null = null;

  constructor(private formService: FormService, private authenticationService: AuthenticationService) {}

  ngOnInit(): void {
    this.connection = this.formService.getFormData().subscribe(formData => {
      this.submitForm(formData);
    });
  }

  submitForm(formData: FormData): void {
    console.log(formData);
    this.authenticationService.authenticateCredentials(formData);
  }

  ngOnDestroy(): void {
    if(this.connection !== null) {
      this.connection.unsubscribe();
    }
  }
}
