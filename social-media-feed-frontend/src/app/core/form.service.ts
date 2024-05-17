import { Injectable } from "@angular/core";
import FieldType from "../shared/interfaces/FieldType";
import FormData from "../shared/interfaces/FormData";
import { Subject } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class FormService {

    formData: Subject<FormData[]> = new Subject<FormData[]>();

    constructor() { }

    getFormData() {
        return this.formData.asObservable();
    }

    updateFormData(formData: FormData[]) {
        this.formData.next(formData);
    }

    createForm(container: Element, fields: FieldType[]): void {
        for(let i = 0; i < fields.length; i++) {
            let field = fields[i];
            
            let label = document.createElement('label');
            label.setAttribute('for', field.name);
            label.textContent = field.name;
            container.appendChild(label);

            let input = document.createElement('input');
            input.setAttribute('type', field.type);
            input.setAttribute('name', field.name);
            input.setAttribute('id', field.name);
            container.appendChild(input);
        }
    }

    // getFormData(fields: FieldType[]): FormData {
    //     const formData: FormData = {};
    //     for(let i = 0; i < fields.length; i++) {
    //         let field = fields[i];
    //         let input = document.querySelector(`input[name=${field.name}]`) as HTMLInputElement;
    //         formData[input.name] = input.value;
    //     }
    //     console.log(formData);
    //     return formData;
    // }
}