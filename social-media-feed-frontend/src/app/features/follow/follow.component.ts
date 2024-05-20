import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { MatButtonModule } from '@angular/material/button';

import { FormComponent } from '../../shared/form/form.component';
import FieldType from '../../shared/interfaces/FieldType';
import Follow from '../../shared/interfaces/Follow';
import FormData from '../../shared/interfaces/FormData';
import { FormService } from '../../core/form.service';

import { Subscription } from 'rxjs';

@Component({
  selector: 'app-follow',
  standalone: true,
  imports: [FormsModule, FormComponent, MatButtonModule],
  templateUrl: './follow.component.html',
  styleUrl: './follow.component.css'
})
export class FollowComponent {
  fields: FieldType[] = [
    { name: "follow", type: "text", icon: "add_reaction", placeholder: 'username or id' },
  ];
  followers: Follow[] = [ { accountId: "", username: "No followers!" } ];
  following: Follow[] = [ { accountId: "", username: "No follows!" } ];
  private connection: Subscription | null = null;


  constructor(private formService: FormService) { }

  ngOnInit(): void {
    this.connection = this.formService.getFormData().subscribe(formData => {
      this.submitForm(formData);
    });
  }

  async submitForm(formData: FormData): Promise<void> {
    console.log(formData);
  }

  ngOnDestroy(): void {
    if(this.connection !== null) {
      this.connection.unsubscribe();
    }
  }
}
