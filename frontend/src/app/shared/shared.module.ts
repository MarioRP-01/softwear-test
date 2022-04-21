import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { NotFoundComponent } from './layout-error/not-found/not-found.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';



@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    NotFoundComponent
  ],
  imports:[
    NgbModule
  ],
  exports: [
    HeaderComponent,
    FooterComponent,
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
