import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import { environment } from "../../environments/environment.development";
import HttpResponseData from "../shared/interfaces/HttpResponseData";
import { AuthenticationService } from "./authentication.service";
import JwtPayload from "../shared/interfaces/JwtPayload";


@Injectable({
    providedIn: 'root'
})
export class FollowService {

    constructor(
        private authenticationService: AuthenticationService,
        private apiService: ApiService) { }

    async followAccount(user: string): Promise<HttpResponseData<any>> {
        const url = environment.apiUrl + 'follow/' + user;
        const response: HttpResponseData<any> = await this.apiService.sendPostRequestWithToken(url, {});
        return response;
    }

    async unfollowAccount(user: string): Promise<HttpResponseData<any>> {
        const url = environment.apiUrl + 'follow/' + user;
        const response: HttpResponseData<any> = await this.apiService.sendDeleteeRequestWithToken(url, {});
        return response;
    }

    async fetchFollowers(): Promise<HttpResponseData<any>> {
        const payload: JwtPayload = this.authenticationService.decodeToken();
        const url = environment.apiUrl + 'account/' + payload.accountId + '/followers';
        const response: HttpResponseData<any> = await this.apiService.sendGetRequestWithToken(url);
        return response;
    }

    async fetchFollowees(): Promise<HttpResponseData<any>> {
        const payload: JwtPayload = this.authenticationService.decodeToken();
        const url = environment.apiUrl + 'account/' + payload.accountId + '/following';
        const response: HttpResponseData<any> = await this.apiService.sendGetRequestWithToken(url);
        return response;
    }
}