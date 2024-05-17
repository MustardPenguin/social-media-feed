import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AuthenticationService } from '../../core/authentication.service';
import { FormService } from '../../core/form.service';
import FieldType from '../../shared/interfaces/FieldType';
import { FormComponent } from '../../shared/form/form.component';


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

  constructor(private authenticationService: AuthenticationService, private formService: FormService) {}

  ngOnInit(): void {
    // const element = document.querySelector('.form') as Element;
    // this.formService.createForm(element, this.fields);
    this.formService.getFormData().subscribe(formData => {
      console.log(formData);
    });
  }

  onSubmit(): void {
    // this.formService.getFormData(this.fields);
    

    this.authenticationService.registerAccount();
  }
}
