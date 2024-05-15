import { Component, Input } from '@angular/core';
import fieldType from '../interfaces/fieldType';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  @Input() fields: fieldType[] = [];
  @Input() submit(): void {
    console.log("No function input given!");
  };

  onSubmit(e: Event): void {
    this.submit();
  }
}
