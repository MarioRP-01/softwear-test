import { Component, OnInit } from '@angular/core';
import { ShopUserService } from '@app/core/api';
import { AuthService } from '@app/core/authentication';
import { ValidateDataService } from '@app/core/service';
import { ShopUser, UserEditProfile as UserEditProgileInterface } from '@app/shared/model';
import { UserEditProfile } from '@app/shared/classes';
import { Observable } from 'rxjs';
import { UserChangePassword } from '@app/shared/model/user-change-password';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {

  public user: UserEditProgileInterface = new UserEditProfile();
  public changePassword!: UserChangePassword;
  public repeatedPassword! : string;

  constructor(
    private userService: ShopUserService,
    private authService: AuthService,
    private validateDataService: ValidateDataService
    ) { }

  ngOnInit(): void {
    
    this.refreshEditProfile();
  }

  refreshEditProfile() {

    this.authService.loadUser().subscribe(
      response => {
        this.user.name = response.name;
        this.user.lastName = response.lastName;
        this.user.address = response.address;
        this.user.email = response.email;
        this.user.mobileNumber = response.mobileNumber;
        this.user.birthdate = response.birthdate;
      }
    );
  }



  isValidEditData(): boolean {

    let isValid = this.validateDataService.isValidEmail(this.user.email);
    
    if (!isValid) {
      // set error message for wrong email.

    }

    if (isValid = this.validateDataService.isValidDate(this.user.birthdate)) {
      // set error message for wrong date.

    }  

    return isValid;
  }

  editProfile() {

    if (this.isValidEditData()) {

      this.userService.editUserProfile(this.user).subscribe(
        response => {
          console.log(response)
          // Create alert success

        },
        error => {
          console.log(error)
          // Create alert error
        }
      );
    }
    console.log("Data is invalid")
    // Create alert error
  }

  checkChangePassword(): boolean {
    let isValidated: boolean = this.validateDataService.matchingPasswords
      (this.changePassword.oldPassword, this.repeatedPassword)

    if (!isValidated) {
      // Create alert passwords doesn't match

    }

    return isValidated

  }

  

  openFormChangePassword() {

  }

}
