import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { AuthService } from '@app/core/authentication';
import { NavigationService } from '@app/core/service';
import { AuthResponse, LoginRequest } from '@app/shared/model';
import { Status } from '@app/shared/data-type'

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent implements OnInit {

  username!: string;
  password!: string;
  error: number = 0;
  isEmpty : boolean = false;

  constructor(private authService: AuthService, private navigationService: NavigationService,
      private router: Router) { }

  ngOnInit(): void {
  }

  areFieldsEmpty(): boolean {

    let isEmpty: boolean = (!this.username || !this.password);
    if (isEmpty) {
      this.isEmpty = true;
      //window.alert("Hay uno o más campos vacíos. Rellénelos antes de continuar");
      // add warning about empty
      
    }
    else{
      this.isEmpty = false;
    }
  
    return isEmpty;
  }

  createLoginRequest(): LoginRequest {

    let loginRequest: LoginRequest = {

      username: this.username,
      password: this.password
    }

    return loginRequest;
  }

  emptyPasswordField(): void {

    this.password = '';
  }

  evaluateResponse(authResponse: AuthResponse): void {

    console.log(Status.SUCCESS.toString())
    if (authResponse.status === Status[Status.SUCCESS]) {
      this.authService.loadUser().subscribe(
        response => this.navigationService.back()
      );

    } else {
      this.emptyPasswordField();

    }
  }

  errorResponse(): void {
    this.emptyPasswordField();
    // Add warning about wrong credentials

  }

  signIn(): void {
    
    if (!this.areFieldsEmpty()) {
      let loginRequest: LoginRequest = this.createLoginRequest();

      this.authService.login(loginRequest).subscribe(
        response => this.evaluateResponse(response),
        error => this.errorResponse()
      ); 

    }
  }

}



export class NgbdAlertBasic {
}

