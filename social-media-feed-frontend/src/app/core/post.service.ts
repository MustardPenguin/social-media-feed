
import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import FormData from "../shared/interfaces/FormData";
import HttpResponseData from "../shared/interfaces/HttpResponseData";
import { environment } from "../../environments/environment.development";
import PostData from "../shared/interfaces/PostData";

@Injectable({
    providedIn: 'root'
})
export class PostService {

    constructor(private apiService: ApiService) { }

    async createPost(formData: FormData): Promise<HttpResponseData<any>> {
        const url = environment.apiUrl + 'post';
        console.log(formData);
        const response: HttpResponseData<any> = await this.apiService.sendPostRequestWithToken(url, formData);
        return response;
    }

    async getPostById(id: string): Promise<HttpResponseData<PostData>> {
        const url = environment.apiUrl + 'post' + "/" + id;
        const response: HttpResponseData<PostData> = await this.apiService.sendGetRequest(url);
        return response;
    }
};