import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { map, Observable } from 'rxjs';

import { Status } from '@app/shared/data-type';
import { AuthResponse, LoginRequest, ShopUser } from '@app/shared/model';
import { ShopUserService } from '../api';

const BASE_URL = '/api/auth'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private httpClient: HttpClient,
    private cookieService: CookieService,
    private shopUserService: ShopUserService
    ) { }

  login(loginRequest: LoginRequest): Observable<AuthResponse> {

    let url: string = BASE_URL + '/login'

    return this.httpClient.post(url, loginRequest).pipe() as Observable<AuthResponse>;
  }

  checkLogin(authResponse: AuthResponse) {

    if (authResponse.status === Status[Status.SUCCESS]) {

      console.log("hola")

      this.shopUserService.getOwnUser().pipe(map(
        response => this.createIDToken(response as ShopUser)
      ))
    }

    console.log("adios")
    return authResponse;
  }

  createIDToken(user: ShopUser): void {
    
    console.log("funciona")
    this.cookieService.set(
      "IDToken",
      JSON.stringify(user)
    )
  }

  isUserLoggedIn(): boolean {

    let exist = false;


    return exist;
  }

}
