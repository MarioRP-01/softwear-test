import { NgModule, Optional, SkipSelf } from '@angular/core';

@NgModule({
  providers: [ /* services */ ],
})
export class CoreModule { 

  // To prevent this module from being imported. It Makes core singleton
  constructor(@Optional() @SkipSelf() core: CoreModule) {
    if(core) {
      throw new Error('You shall not run')
    }
  }
}