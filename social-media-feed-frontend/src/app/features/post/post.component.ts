import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';

import { FormComponent } from '../../shared/form/form.component';
import FieldType from '../../shared/interfaces/FieldType';

import { TextFieldModule } from '@angular/cdk/text-field';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { PostService } from '../../core/post.service';
import HttpResponseData from '../../shared/interfaces/HttpResponseData';

@Component({
  selector: 'app-post',
  standalone: true,
  imports: [
    MatFormFieldModule, MatIconModule, MatInputModule, MatButtonModule,
    FormsModule, ReactiveFormsModule, TextFieldModule,
  ],
  templateUrl: './post.component.html',
  styleUrl: './post.component.css'
})
export class PostComponent {
  formData: FormGroup = this.formBuilder.group({
    title: '',
    description: '',
  });

  constructor(private formBuilder: FormBuilder, private postService: PostService) {}

  async onSubmit(): Promise<void> {
    const data = this.formData.value;
    data.title = data.title.trim();
    if(data.title === '' || data.description.trim() === '') {
      window.alert("Title and description are required.");
      return;
    }
    const response: HttpResponseData<any> = await this.postService.createPost(data);
    if(response.ok === true) {
      window.alert("Post created successfully.");
    } else {
      window.alert("Failed to create post.");
    }
  }
}
