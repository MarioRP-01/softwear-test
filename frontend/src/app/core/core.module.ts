import { NgModule, Optional, SkipSelf } from '@angular/core';

import { ProductService, ShopUserService, TransactionService } from './api';
import { AuthGuard, AuthService } from './authentication'

@NgModule({
  providers: [
    ProductService,
    ShopUserService,
    TransactionService,
    AuthGuard,
    AuthService
  ],
})
export class CoreModule { 

  // To prevent this module from being imported. It Makes core singleton
  constructor(@Optional() @SkipSelf() core: CoreModule) {
    if(core) {
      throw new Error('You shall not run')
    }
  }
}