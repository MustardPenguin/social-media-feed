import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import FormData from '../shared/interfaces/FormData';

import { environment } from '../../environments/environment.development';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private token: String = "";

  constructor(private apiService: ApiService) { }

  async authenticateCredentials(formData: FormData): Promise<void> {
    const loginUrl = environment.apiUrl + 'authenticate';
    const response: any = await this.apiService.sendPostRequest(loginUrl, formData);
    if(response.ok === true) {
      this.token = response.body.token;
    }
    return response;
  }

  getToken(): String {
    return this.token;
  }
}