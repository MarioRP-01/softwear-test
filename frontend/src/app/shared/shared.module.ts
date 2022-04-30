import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { NotFoundComponent } from './layout-error/not-found/not-found.component';
import { NgChartsModule } from 'ng2-charts';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    NotFoundComponent
  ],
  imports:[
    NgbModule,
    CommonModule,
    RouterModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    FontAwesomeModule,
    NgChartsModule,

    NgbModule,

    HeaderComponent,
    FooterComponent,
    RouterModule
  ]
})
/*No services */
export class SharedModule { 

  static forRoot() {
    return {
      ngModule: SharedModule,
      providers: [ /* Services */ ]
    }
  }
}
