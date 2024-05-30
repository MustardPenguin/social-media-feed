import { Component, Input } from '@angular/core';
import { FormsModule, FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

import { FormService } from '../../core/form.service';
import FormData from '../interfaces/FormData';
import FieldType from '../interfaces/FieldType';


@Component({
  selector: 'app-form',
  standalone: true,
  imports: [
    MatFormFieldModule, MatIconModule, MatInputModule, MatButtonModule,
    FormsModule, ReactiveFormsModule
  ],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  @Input() fields: FieldType[] = [];
  formData: FormGroup = this.formBuilder.group({});

  constructor(private formService: FormService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    const formData: FormData = {};
    for(let i = 0; i < this.fields.length; i++) {
      const field = this.fields[i];
      formData[field.name] = "";
    }
    this.formData = this.formBuilder.group(formData);
  }

  onSubmit(): void {
    const formData: FormData = this.formData.value;
    for(let i = 0; i < this.fields.length; i++) {
      const field = this.fields[i];
      if(!formData[field.name]) {
        window.alert(`Please fill in the ${field.name} field`);
        return;
      }
    }
    this.formService.updateFormData(formData);
  }
}
