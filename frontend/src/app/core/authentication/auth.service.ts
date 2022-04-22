import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ShopUser } from '@app/shared/model';
import { LoginRequest } from '@app/shared/model/login-request';
import { Observable } from 'rxjs';

const BASE_URL = '/api/auth'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private HttpClient: HttpClient) { }

  login(loginRequest: LoginRequest): Observable<boolean> {

    let url: string = BASE_URL + '/login'
    return this.HttpClient.post(url, loginRequest).pipe() as Observable<boolean>;
  }

  /*
  register(): Observable<ShopUser> {

    let url: string = BASE_URL + '/register'
  }
  */
}
