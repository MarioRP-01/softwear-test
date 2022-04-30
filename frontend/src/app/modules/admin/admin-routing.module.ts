import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { FillingTableComponent } from './components/filling-table/filling-table.component';
import { AdminHomeComponent } from './pages/admin-home/admin-home/admin-home.component';
import { ManageProductsComponent } from './pages/manage-products/manage-products/manage-products.component';
import { ManageUsersComponent } from './pages/manage-users/manage-users/manage-users.component';

const routes: Routes = [
  { 
    path: 'home',
    component: AdminHomeComponent,
    resolve: {
      
    }
  },

  { 
    path: 'manage',
    component: AdminComponent,
    children: [
      { 
        path: 'products',
        component: ManageProductsComponent,
        resolve: {

        }
      },

      { 
        path: 'users',
        component: ManageUsersComponent,
        resolve: {

        }
      },

      { path: '', redirectTo: '/admin/home', pathMatch: 'full' }
    ]
   },

  { path: '', redirectTo: 'home' },
  { path: 'fillingtable', component: FillingTableComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
