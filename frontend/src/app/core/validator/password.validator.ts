import { Injectable } from "@angular/core";
import { AbstractControl }  from '@angular/forms';
import { ValidationErrors } from '@angular/forms';

@Injectable({providedIn : 'root'})
export class PasswordValidator {

    constructor() {}

    public static passwordMatchValidator(control: AbstractControl): ValidationErrors | null {

        if (!control.get('password1')?.value || !control.get('password2')?.value) {

            return null;
        }
      
        let isValid: boolean = control.get('password1')?.value == control.get('password2')?.value;

        // Invalid
        if (!isValid)
            return { invalidPasswordMatch : true };

        // Valid
        return null
    }
}