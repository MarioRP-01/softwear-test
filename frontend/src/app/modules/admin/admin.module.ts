import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { AdminHomeComponent } from './pages/admin-home/admin-home/admin-home.component';
import { ManageProductsComponent } from './pages/manage-products/manage-products/manage-products.component';
import { ManageUsersComponent } from './pages/manage-users/manage-users/manage-users.component';
import { FillingTableComponent } from './components/filling-table/filling-table.component';


@NgModule({
  declarations: [
    AdminComponent,
    AdminHomeComponent,
    ManageProductsComponent,
    ManageUsersComponent,
    FillingTableComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
