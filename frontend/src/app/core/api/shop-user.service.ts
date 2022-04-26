import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ShopUser, UserRegister, LoginRequest, UserEditProfile, UserChangePassword } from '@app/shared/model'
import { Observable } from 'rxjs';

const BASE_URL = '/api/users'

@Injectable({
  providedIn: 'root'
})
export class ShopUserService {

  constructor(private httpClient: HttpClient) { }

  getOwnUser(): Observable<ShopUser> {

    let url: string = BASE_URL + `/my`;
    return this.httpClient.get(url).pipe() as Observable<ShopUser>;
  }

  createUser(userRegister: UserRegister): Observable<ShopUser> {

    let url: string = BASE_URL;
    return this.httpClient.post(url, userRegister).pipe() as Observable<ShopUser>;
  }

  editUserProfile(userEditProfile: UserEditProfile): Observable<ShopUser> {

    let url: string = BASE_URL + '/my';
    return this.httpClient.put(url, userEditProfile).pipe() as Observable<ShopUser>;
  }

  changePassword(changePassword: UserChangePassword): Observable<ShopUser> {

    let url: string = BASE_URL + '/my/password'
    return this.httpClient.put(url, changePassword).pipe() as Observable<ShopUser>
  }

}
