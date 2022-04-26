import { Component, OnInit } from '@angular/core';
import { ShopUserService } from '@app/core/api';
import { AuthService } from '@app/core/authentication';
import { ValidateDataService } from '@app/core/service';
import { UserChangePassword, UserEditProfile as UserEditProgileInterface } from '@app/shared/model';
import { UserEditProfile } from '@app/shared/classes';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ChangePasswordComponent } from '../change-password/change-password.component';

@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.css']
})
export class AccountDetailsComponent implements OnInit {

  public user: UserEditProgileInterface = new UserEditProfile();

  constructor(
    private userService: ShopUserService,
    private authService: AuthService,
    private validateDataService: ValidateDataService,
    private modalService: NgbModal
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

  openFormChangePassword() {

    const modalRef = this.modalService.open(ChangePasswordComponent)
    modalRef.componentInstance.id = this.authService.getUserId;

    modalRef.result.then(
      (result) => {
        let userChangePassword: UserChangePassword = {
          oldPassword: result.oldPassword,
          newPassword: result.password1
        }
        this.userService.changePassword(userChangePassword).subscribe(
          response => {
            console.log(response)
            // success alert
          },
          error => {
            console.log(error)
            // error alert
          })
      }).catch(
        (error) => {
          console.log(error)
        
      })
  }
}
