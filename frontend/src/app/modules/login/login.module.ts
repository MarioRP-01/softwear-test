import { NgModule } from '@angular/core';

import { LoginRoutingModule } from './login-routing.module';
import { LoginComponent } from './login.component';
import { LoginUsComponent } from './pages/login-us/login-us.component';
import { SignInComponent } from './components/sign-in/sign-in.component';
import { SignUpComponent } from './components/sign-up/sign-up.component';
import { SharedModule } from '@app/shared/shared.module';


@NgModule({
  declarations: [
    LoginComponent,
    LoginUsComponent,
    SignInComponent,
    SignUpComponent
  ],
  imports: [
    SharedModule,
    LoginRoutingModule
  ]
})
export class LoginModule { }
