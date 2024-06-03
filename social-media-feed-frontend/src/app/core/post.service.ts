
import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import FormData from "../shared/interfaces/FormData";
import HttpResponseData from "../shared/interfaces/HttpResponseData";
import { environment } from "../../environments/environment.development";

@Injectable({
    providedIn: 'root'
})
export class PostService {

    constructor(private apiService: ApiService) { }

    async createPost(formData: FormData) {
        const url = environment.apiUrl + 'post';
        console.log(formData);
        const response: HttpResponseData<any> = await this.apiService.sendPostRequestWithToken(url, formData);
        return response;
    }

    async getPosts() {
        const url = environment.apiUrl + 'post' + "/";
        const response: HttpResponseData<any> = await this.apiService.sendGetRequestWithToken(url);
        return response;
    }
};