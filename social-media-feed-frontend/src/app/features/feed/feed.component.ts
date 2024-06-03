import { Component } from '@angular/core';
import { PostService } from '../../core/post.service';

@Component({
  selector: 'app-feed',
  standalone: true,
  imports: [],
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.css'
})
export class FeedComponent {
  posts: any = [];

  constructor(private postService: PostService) {}
  

}
