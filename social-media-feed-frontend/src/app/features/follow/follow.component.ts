import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { MatButtonModule } from '@angular/material/button';

import { FormComponent } from '../../shared/form/form.component';
import FieldType from '../../shared/interfaces/FieldType';
import Follow from '../../shared/interfaces/Follow';
import FormData from '../../shared/interfaces/FormData';
import { FormService } from '../../core/form.service';

import { Subscription } from 'rxjs';
import { FollowService } from '../../core/follow.service';
import { HttpResponse } from '@angular/common/http';
import HttpResponseData from '../../shared/interfaces/HttpResponseData';

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
  followers: Follow[] = [];
  followees: Follow[] = [];
  private connection: Subscription | null = null;


  constructor(private formService: FormService, private followService: FollowService) { }

  async ngOnInit(): Promise<void> {

    let followees: HttpResponseData<any> = await this.followService.fetchFollowees();
    this.followees = followees.body.follows;

    let followers: HttpResponseData<any> = await this.followService.fetchFollowers();
    this.followers = followers.body.follows;

    this.connection = this.formService.getFormData().subscribe(formData => {
      this.submitForm(formData);
    });
  }

  async submitForm(formData: FormData): Promise<void> {
    console.log(formData);
    const user = formData['follow'];
    const response: HttpResponseData<any> = await this.followService.followAccount(user);
    console.log(response);
    if(response.ok) {
      window.alert(`Successfully followed ${user}`);
      this.followees.push({ accountId: response.body.accountId, username: response.body.username });
    } else {
      window.alert(response.body.error);
    }
  }

  unfollow(accountId: string): void {
    console.log(`unfollow ${accountId}`);
    this.followService.unfollowAccount(accountId);
  }

  ngOnDestroy(): void {
    if(this.connection !== null) {
      this.connection.unsubscribe();
    }
  }
}
