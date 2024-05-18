import { Component, Input } from '@angular/core';

import { FormComponent } from '../../shared/form/form.component';
import { FormService } from '../../core/form.service';

import FieldType from '../../shared/interfaces/FieldType';
import FormData from '../../shared/interfaces/FormData';
import { Observable, Subscription } from 'rxjs';
import { AuthenticationService } from '../../core/authentication.service';
import HttpResponseData from '../../shared/interfaces/HttpResponseData';

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

  async submitForm(formData: FormData): Promise<void> {
    console.log(formData);
    const response: HttpResponseData<any> = await this.authenticationService.authenticateCredentials(formData);
    console.log(response);
    if(response.ok === true) {
      window.alert(response.body.message);
    } else {
      window.alert(response.body.error);
    }
  }

  ngOnDestroy(): void {
    if(this.connection !== null) {
      this.connection.unsubscribe();
    }
  }
}
