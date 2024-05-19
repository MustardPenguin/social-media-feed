import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import FormData from '../shared/interfaces/FormData';

import { environment } from '../../environments/environment.development';
import HttpResponseData from '../shared/interfaces/HttpResponseData';
import { lastValueFrom } from 'rxjs';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import JwtPayload from '../shared/interfaces/JwtPayload';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  authenticated: boolean = false;
  private token: String = "";

  constructor(private http: HttpClient) { }

  async authenticateCredentials(formData: FormData): Promise<HttpResponseData<any>> {
    const response: HttpResponseData<any> = await this.sendAuthenticateRequest(formData);
    if(response.ok === true) {
      this.token = response.body.token;
      this.authenticated = true;
    }
    return response;
  }

  isAuthenticated(): boolean {
    return this.authenticated;
  }

  getToken(): String {
    return this.token;
  }

  logout(): void {
    this.authenticated = false;
    this.token = "";
  }

  decodeToken(): JwtPayload {
    const token = this.token;
    if(token === "") {
      throw new Error("No token found");
    }
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    const payload: JwtPayload = JSON.parse(jsonPayload);
    return payload;
  }

  async sendAuthenticateRequest(data: FormData): Promise<HttpResponseData<any>> {
    const url = environment.apiUrl + 'authenticate';
    return lastValueFrom(this.http.post<any>(url, data, { observe: 'response' }))
            .then((response: HttpResponse<any>) => {
                console.log(response);
                const httpResponseData: HttpResponseData<any> = {
                    statusCode: response.status,
                    body: response.body,
                    ok: response.ok
                };
                return httpResponseData;
            }).catch((error: HttpErrorResponse) => {
                console.log(error);
                const httpResponseData: HttpResponseData<any> = {
                    statusCode: error.status,
                    body: { error: error.error },
                    ok: error.ok
                };
                return httpResponseData;
            });
  }
}