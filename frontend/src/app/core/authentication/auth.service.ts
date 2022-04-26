import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';

import { Status } from '@app/shared/data-type';
import { AuthResponse, LoginRequest, ShopUser } from '@app/shared/model';
import { ShopUserService } from '../api';

const BASE_URL = '/api/auth'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  userData: ShopUser | undefined;

  constructor(
    private httpClient: HttpClient,
    private shopUserService: ShopUserService
    ) { }

  login(loginRequest: LoginRequest): Observable<AuthResponse> {

    let url: string = BASE_URL + `/login`

    return this.httpClient.post(url, loginRequest).pipe() as Observable<AuthResponse>;
  }

  logout(): Observable<AuthResponse> {
    
    let url: string = BASE_URL + `logout`;

    return this.httpClient.post(url, null).pipe() as Observable<AuthResponse>
  }

  loadUser(): Observable<ShopUser> {

    return this.shopUserService.getOwnUser().pipe(map(
      response => this.userData = response,
      (error: String) => {
        this.userData = undefined;
        console.log(error);
      }
    ))
  }

  isUserLoggedIn(): boolean {

    return this.userData !== undefined;
  }

  getUserData(): ShopUser | null {

    if (this.isUserLoggedIn()) {
      return this.userData as ShopUser;

    } else {
      return null;

    }
  }

  getUserId(): number {
    if (this.isUserLoggedIn()) {

      let user = this.userData as ShopUser
      return user.id;
    }
  
    return 0
  }
}
