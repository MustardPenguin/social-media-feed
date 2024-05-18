import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import FormData from '../shared/interfaces/FormData';

import { environment } from '../../environments/environment.development';
import { HttpResponse } from '@angular/common/http';
import HttpResponseData from '../shared/interfaces/HttpResponseData';

@Injectable({
  providedIn: 'root',
})
export class AccountService {

  constructor(private apiService: ApiService) { }

  async registerAccount(formData: FormData): Promise<HttpResponseData<any>> {
    const registerUrl = environment.apiUrl + 'account';
    const response: HttpResponseData<any> = await this.apiService.sendPostRequest(registerUrl, formData);
    return response;
  }
}