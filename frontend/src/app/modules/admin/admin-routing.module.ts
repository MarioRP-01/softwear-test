import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin.component';
import { FillingTableComponent } from './components/filling-table/filling-table.component';

const routes: Routes = [
  { path: '', component: AdminComponent },
  { path: 'fillingtable', component: FillingTableComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
