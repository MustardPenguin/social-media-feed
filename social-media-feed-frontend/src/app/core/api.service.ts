
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription, catchError, lastValueFrom, map, throwError } from 'rxjs';

import { AuthenticationService } from './authentication.service';
import FormData from '../shared/interfaces/FormData';
import HttpResponseData from '../shared/interfaces/HttpResponseData';

@Injectable({
  providedIn: 'root',
})
export class ApiService {

    constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }

    async sendPostRequest(url: string, data: FormData, headers: FormData = { }): Promise<HttpResponseData<any>> {
        return lastValueFrom(this.http.post<any>(url, data, { observe: 'response', headers: headers }))
            .then((response: HttpResponse<any>) => this.handleResponse(response))
            .catch((error: HttpErrorResponse) => this.handleError(error));
    }

    async sendPostRequestWithToken(url: string, data: FormData, headers: FormData = { }): Promise<HttpResponseData<any>> {
        const token: string = this.authenticationService.getToken();
        headers['Authorization'] = `Bearer ${token}`;
        return lastValueFrom(this.http.post<any>(url, data, { observe: 'response', headers: headers }))
            .then((response: HttpResponse<any>) => this.handleResponse(response))
            .catch((error: HttpErrorResponse) => this.handleError(error));
    }

    async sendGetRequest(url: string, headers: FormData = { }): Promise<HttpResponseData<any>> {
        return lastValueFrom(this.http.get<any>(url, { observe: 'response', headers: headers }))
            .then((response: HttpResponse<any>) => this.handleResponse(response))
            .catch((error: HttpErrorResponse) => this.handleError(error));
    }

    async sendGetRequestWithToken(url: string, headers: FormData = { }): Promise<HttpResponseData<any>> {
        const token: string = this.authenticationService.getToken();
        headers['Authorization'] = `Bearer ${token}`;
        return lastValueFrom(this.http.get<any>(url, { observe: 'response', headers: headers }))
            .then((response: HttpResponse<any>) => this.handleResponse(response))
            .catch((error: HttpErrorResponse) => this.handleError(error));
    }

    async sendDeleteeRequestWithToken(url: string, headers: FormData = { }): Promise<HttpResponseData<any>> {
        const token: string = this.authenticationService.getToken();
        headers['Authorization'] = `Bearer ${token}`;
        return lastValueFrom(this.http.delete<any>(url, { observe: 'response', headers: headers }))
            .then((response: HttpResponse<any>) => this.handleResponse(response))
            .catch((error: HttpErrorResponse) => this.handleError(error));
    }

    private handleResponse(response: HttpResponse<any>): HttpResponseData<any> {
        console.log(response);
        const httpResponseData: HttpResponseData<any> = {
            statusCode: response.status,
            body: response.body,
            ok: response.ok
        };
        return httpResponseData;
    }

    private handleError(error: HttpErrorResponse): HttpResponseData<any> {
        console.log(error);
        const httpResponseData: HttpResponseData<any> = {
            statusCode: error.status,
            body: { error: error.error },
            ok: error.ok
        };
        return httpResponseData;
    };
}