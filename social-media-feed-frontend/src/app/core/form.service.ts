import { Injectable } from "@angular/core";
import FieldType from "../shared/interfaces/FieldType";
import FormData from "../shared/interfaces/FormData";

@Injectable({
    providedIn: 'root'
})
export class FormService {

    constructor() { }

    createForm(container: Element | null, fields: FieldType[]): void {
        if(!container) {
            throw new Error('Form container not found!');
        }
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

    getFormData(fields: FieldType[]): FormData {
        const formData: FormData = {};
        for(let i = 0; i < fields.length; i++) {
            let field = fields[i];
            let input = document.querySelector(`input[name=${field.name}]`) as HTMLInputElement;
            formData[input.name] = input.value;
        }
        console.log(formData);
        return formData;
    }
}