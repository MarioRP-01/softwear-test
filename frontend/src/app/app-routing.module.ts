import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotFoundComponent } from '@app/shared/layout-error/not-found/not-found.component';

const routes: Routes = [
  { path: 'not-found', component: NotFoundComponent},
  { path: '', loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule) },
  { path: 'login', loadChildren: () => import('./modules/login/login.module').then(m => m.LoginModule) },
  { path: 'profile', loadChildren: () => import('./modules/user/user.module').then(m => m.UserModule) },
  { path: 'product-view', loadChildren: () => import('./modules/product/product.module').then(m => m.ProductModule) },
  { path: 'transaction', loadChildren: () => import('./modules/transaction/transaction.module').then(m => m.TransactionModule) },
  { path: '**', redirectTo: "not-found"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
