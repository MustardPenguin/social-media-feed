import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import { environment } from "../../environments/environment.development";


@Injectable({
    providedIn: 'root'
})
export class FollowService {

    constructor(private apiService: ApiService) { }

    async followAccount(accountId: string): Promise<void> {
        const url = environment.apiUrl + '/follow' + accountId;
        
    }
}