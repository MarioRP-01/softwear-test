import { NgModule } from '@angular/core';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { AdminHomeComponent } from './pages/admin-home/admin-home/admin-home.component';
import { ManageProductsComponent } from './pages/manage-products/manage-products/manage-products.component';
import { ManageUsersComponent } from './pages/manage-users/manage-users/manage-users.component';
import { FillingTableComponent } from './components/filling-table/filling-table.component';
import { SharedModule } from '@app/shared/shared.module';
import { IncomeChartComponent } from './components/income-chart/income-chart.component';
import { SaleChartComponent } from './components/sale-chart/sale-chart.component';


@NgModule({
  declarations: [
    AdminComponent,
    AdminHomeComponent,
    ManageProductsComponent,
    ManageUsersComponent,
    FillingTableComponent,
    IncomeChartComponent,
    SaleChartComponent
  ],
  imports: [
    SharedModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
