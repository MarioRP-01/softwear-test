import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ShopUserService } from '@app/core/api';
import { ValidateDataService } from '@app/core/service';
import { PasswordValidator } from '@app/core/validator';
import { UserChangePassword } from '@app/shared/model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  @Input() 
  id!: number;
  
  changePasswordForm!: FormGroup;

  constructor(
    public activeModal: NgbActiveModal,
    private formBuilder: FormBuilder
    ) { 
      this.createForm();
    }

  ngOnInit(): void {

  }

  createForm() {

    this.changePasswordForm = this.formBuilder.group({
      oldPassword: new FormControl('', [
        Validators.required
      ]),

      password1: new FormControl('', [
        Validators.required
      ]),

      password2: new FormControl('', [
        Validators.required
      ]),
    },
    { validator: PasswordValidator.passwordMatchValidator});
  }

  submitForm() {

    this.activeModal.close(this.changePasswordForm.value);
  }

  closeModalChangePassword() {

    this.activeModal.dismiss('Modal Closed');
  }
}