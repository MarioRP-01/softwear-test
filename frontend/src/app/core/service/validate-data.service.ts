import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ValidateDataService {

  constructor() { }

  isValidEmail(email: string): boolean {

    let regex = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

    return email.match(regex) != null;
  }

  isValidDate(date: string): boolean {
    let regex = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d$"

    return date.match(regex) != null;
  }

  /*
  isValidMobileNumber(mobileNumber: string): boolean {

    let regex = "[0-9]{9}"

    return mobileNumber.match(regex) != null;

  }
  */

  matchingPasswords(password: string, repeatedPassword: string): boolean {
 
    return password == repeatedPassword;
  }

}
