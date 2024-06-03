
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { AuthenticationService } from './authentication.service';
import JwtPayload from '../shared/interfaces/JwtPayload';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedService<T> {
  eventSource: EventSource | null = null;
  eventSubject: Subject<T> = new Subject<T>();

  constructor(private authenticationService: AuthenticationService) {}

  createEventSource(): EventSource {
    this.closeEventSource();

    const payload: JwtPayload = this.authenticationService.decodeToken();
    const url: string = environment.apiUrl + "feed" + "/" + payload.accountId;
    this.eventSource = new EventSource(url, {});

    this.eventSource.onmessage = (event) => {
      const data: T = JSON.parse(event.data);
      this.eventSubject.next(data);
    };

    return this.eventSource;
  }

  closeEventSource(): void {
    if(this.eventSource !== null) {
      console.log("Closing event source...");
      this.eventSource.close();
    }
  }

  getEventSubject(): Subject<T> {
    return this.eventSubject;
  }
}
