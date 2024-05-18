import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AccountService } from '../../core/account.service';
import { FormService } from '../../core/form.service';
import { FormComponent } from '../../shared/form/form.component';

import FieldType from '../../shared/interfaces/FieldType';
import FormData from '../../shared/interfaces/FormData';
import { Subscription } from 'rxjs';
import HttpResponseData from '../../shared/interfaces/HttpResponseData';


@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, FormComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  fields: FieldType[] = [
    { name: "username", type: "text", icon: "person" },
    { name: "password", type: "password", icon: "password" }
  ];

  private connection: Subscription | null = null;

  constructor(private accountService: AccountService, private formService: FormService) {}

  ngOnInit(): void {
    this.connection = this.formService.getFormData().subscribe(formData => {
      this.submitForm(formData);
    });
  }

  async submitForm(formData: FormData): Promise<void> {
    console.log(formData);
    const response: HttpResponseData<any> = await this.accountService.registerAccount(formData);
    console.log(response)
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
