import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthResponse, LoginRequest } from '@app/shared/model';
import { Observable } from 'rxjs';

const BASE_URL = '/api/auth'

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private HttpClient: HttpClient) { }

  login(loginRequest: LoginRequest): Observable<AuthResponse> {

    let url: string = BASE_URL + '/login'
    return this.HttpClient.post(url, loginRequest).pipe() as Observable<AuthResponse>;
  }


}
