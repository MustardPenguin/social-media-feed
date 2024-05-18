import { Injectable } from "@angular/core";
import FieldType from "../shared/interfaces/FieldType";
import FormData from "../shared/interfaces/FormData";
import { Subject } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class FormService {

    formData: Subject<FormData> = new Subject<FormData>();

    constructor() { }

    getFormData() {
        return this.formData.asObservable();
    }

    updateFormData(formData: FormData) {
        this.formData.next(formData);
    }
}