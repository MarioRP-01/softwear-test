import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ShopUser, UserRegister, LoginRequest } from '@app/shared/model'
import { Observable } from 'rxjs';

const BASE_URL = '/api/users'

@Injectable({
  providedIn: 'root'
})
export class ShopUserService {

  constructor(private httpClient: HttpClient) { }

  createUser(userRegister: UserRegister): Observable<ShopUser> {

    let url: string = BASE_URL;
    return this.httpClient.post(url, userRegister).pipe() as Observable<ShopUser>;
  }

}
