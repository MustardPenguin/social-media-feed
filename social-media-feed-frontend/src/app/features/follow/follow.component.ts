import { Component } from '@angular/core';
import { FormComponent } from '../../shared/form/form.component';
import { FormsModule } from '@angular/forms';
import FieldType from '../../shared/interfaces/FieldType';
import Follow from '../../shared/interfaces/Follow';

@Component({
  selector: 'app-follow',
  standalone: true,
  imports: [FormsModule, FormComponent],
  templateUrl: './follow.component.html',
  styleUrl: './follow.component.css'
})
export class FollowComponent {
  fields: FieldType[] = [
    { name: "follow", type: "text", icon: "add_reaction", placeholder: 'username or id' },
  ];

  followers: Follow[] = [
    { accountId: '956c253b-0d4f-457e-a96d-31cce7639eaa', username: 'user1' },
    { accountId: '956c253b-0d4f-457e-a96d-31cce7639eaa', username: 'user2' },
    { accountId: '956c253b-0d4f-457e-a96d-31cce7639eaa', username: 'user3' }
  ];
  following: Follow[] = [
    { accountId: '956c253b-0d4f-457e-a96d-31cce7639eaa', username: 'user4' },
    { accountId: '956c253b-0d4f-457e-a96d-31cce7639eaa', username: 'user5' },
    { accountId: '956c253b-0d4f-457e-a96d-31cce7639eaa', username: 'user6' }
  ];


}
