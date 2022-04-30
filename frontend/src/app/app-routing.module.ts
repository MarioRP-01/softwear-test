import { NgModule } from '@angular/core';
import { RouterModule, Routes, PreloadAllModules } from '@angular/router';
import { NotFoundComponent } from '@app/shared/layout-error/not-found/not-found.component';
import { AuthGuard } from './core/authentication';
import { AdminAuthGuard } from './core/authentication/admin-auth.guard';

const routes: Routes = [
  { path: 'not-found', component: NotFoundComponent},
  { path: 'home', loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule) },

  { path: 'login', loadChildren: () => import('./modules/login/login.module').then(m => m.LoginModule) },

  { 
    path: 'profile',
    canLoad: [AuthGuard],
    canActivate: [AuthGuard],
    loadChildren: () => import('./modules/user/user.module').then(m => m.UserModule) 
  },

  { path: 'product', loadChildren: () => import('./modules/product/product.module').then(m => m.ProductModule) },

  { 
    path: 'admin',
    canActivate: [AdminAuthGuard],
    canLoad: [AdminAuthGuard],
    loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule) },

  { 
    path: 'transaction', 
    canActivate: [AuthGuard],
    canLoad: [AuthGuard],
    loadChildren: () => import('./modules/transaction/transaction.module').then(m => m.TransactionModule) 
  },

  { path: '', redirectTo: 'home', pathMatch: 'full'},
  { path: '**', redirectTo: "not-found"}
];

@NgModule({
  imports: [RouterModule.forRoot(
    routes,
    {
      preloadingStrategy: PreloadAllModules
    })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
