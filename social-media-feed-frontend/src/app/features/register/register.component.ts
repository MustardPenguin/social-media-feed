import { Component } from '@angular/core';
import { FormComponent } from '../../shared/form/form.component';
import fieldType from '../../shared/interfaces/fieldType';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormComponent],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  fields: fieldType[] = [
    { name: "username", type: "text" },
    { name: "password", type: "password" }
  ];

  submit(): void {
    console.log('submit registration!');
  }

  
}
