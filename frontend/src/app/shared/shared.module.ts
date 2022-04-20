import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  exports: []
})
/*No providers*/
export class SharedModule { 

  static forRoot() {
    return {
      ngModule: SharedModule,
      providers: [ /* Services */]
    }
  }
}
