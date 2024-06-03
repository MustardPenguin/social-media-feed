import { Component } from '@angular/core';
import { PostService } from '../../core/post.service';
import { FeedService } from '../../core/feed.service';
import { Subscription } from 'rxjs';

interface PostReceivedEvent {
  accountId: string;
  postId: string;
}

@Component({
  selector: 'app-feed',
  standalone: true,
  imports: [],
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.css'
})
export class FeedComponent {
  private subscription: Subscription | null = null;
  posts: any = [];

  constructor(private postService: PostService, private feedService: FeedService<PostReceivedEvent>) {}

  ngOnInit(): void {
    this.feedService.createEventSource();
    this.subscription = this.feedService.getEventSubject().subscribe((postReceivedEvent: PostReceivedEvent) => {
      console.log("Post received event: ", postReceivedEvent);
      
    });
  }

  ngOnDestroy(): void {
    this.feedService.closeEventSource();
    this.subscription?.unsubscribe();
  }
}
