import { Component, OnInit } from '@angular/core';
import { ShopUserService } from '@app/core/api';
import { AuthService } from '@app/core/authentication';
import { NavigationService } from '@app/core/service';
import { LoginRequest, UserRegister } from '@app/shared/model';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  username!: string;
  email!: string;
  password!: string;
  repeatedPassword!: string;

  constructor(private userService: ShopUserService, private authService: AuthService,
      private navigationService: NavigationService) { }

  ngOnInit(): void {
  }

  areFieldsEmpty(): boolean {

    let isEmpty: boolean = (!this.username || !this.password ||
        !this.email || !this.repeatedPassword);
    if (isEmpty) {
      // add warning about empty

    }
  
    return isEmpty;
  }

  emptyPasswords(): void {
    
    this.password = '';
    this.repeatedPassword = '';
  }

  matchingPasswords(): boolean {

    let isMatch: boolean =  this.password == this.repeatedPassword;
    if (!isMatch) {
      // add warning about passwords not matching
      this.emptyPasswords;

    }
    return isMatch
  }

  validEmail(): boolean {

    let regex = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
    let isValid = this.email.match(regex) != null;

    if (!isValid) {
      // add warning about not valid email

    }
    return isValid;
  }

  createUserRegister(): UserRegister {

    let userRegister: UserRegister = {

      username: this.username,
      email: this.email,
      password: this.password
    }

    return userRegister;
  }

  createLoginRequest(): LoginRequest {
    let loginRequest: LoginRequest = {

      username: this.username,
      password: this.password
    }

    return loginRequest;
  }

  logWithUser(): void {
    let loginRequest: LoginRequest = this.createLoginRequest();

    this.authService.login(loginRequest).subscribe(
      response => this.navigationService.back()
    );
  }
  
  emptyFields(): void {
    this.username = '';
    this.password = '';
    this.email = '';
    this.repeatedPassword = '';
  }

  errorResponse(): void {

    //add warning about error with server / existing username
    this.emptyFields();
  }

  isValidRegistration(): boolean {

    let isValid: boolean = !this.areFieldsEmpty();

    if (isValid) {
      isValid =  this.matchingPasswords() && this.validEmail();

    }

    return isValid;
  }

  signUp(): void {

    if (this.isValidRegistration()) {
      let userRegister: UserRegister = this.createUserRegister()

      this.userService.createUser(userRegister).subscribe(
        response => this.logWithUser(),
        error => this.errorResponse()
      )

    }
  }
}
