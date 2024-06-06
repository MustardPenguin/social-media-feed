import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { PostService } from '../../core/post.service';
import { FeedService } from '../../core/feed.service';
import { Subscription } from 'rxjs';
import HttpResponseData from '../../shared/interfaces/HttpResponseData';
import PostData from '../../shared/interfaces/PostData';

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
  posts: PostData[] = [
    { accountId: '1', title: 'Post 1', description: 'Content 1', postId: "1", username: 'Author 1', createdAt: "2020-01-01" },
  ];

  constructor(private postService: PostService, private changeDetectorRef: ChangeDetectorRef,
    private feedService: FeedService<PostReceivedEvent>) {}

  async ngOnInit(): Promise<void> {
    this.feedService.createEventSource();

    this.subscription = this.feedService.getEventSubject()
      .subscribe((postReceivedEvent: PostReceivedEvent) => this.onPostReceived(postReceivedEvent));
  }

  async onPostReceived(postReceivedEvent: PostReceivedEvent): Promise<void> {
    console.log("Post received event: ", postReceivedEvent);
    await this.postService.getPostById(postReceivedEvent.postId)
      .then((response: HttpResponseData<any>) => {
        if(response.ok) {
          this.addPostToFeed(response.body);
        } else {
          console.log("Error receiving post created event, error: " + response.body.error);
        }
    }).catch((error: any) => {
        console.error("Error getting post by id: ", error);
    });
  }

  addPostToFeed(postData: PostData): void {
    console.log(postData);
    this.posts = this.posts.slice(0).concat(postData);
    // Force angular to check detect changes since updating the this.post array does not trigger change detection for some reason???
    this.changeDetectorRef.detectChanges();
  }

  ngOnDestroy(): void {
    this.feedService.closeEventSource();
    if(this.subscription !== null) {
      this.subscription.unsubscribe();
    }
  }
}
