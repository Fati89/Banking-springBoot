import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Accounts } from './accounts/accounts';
import { Customers } from './customers/customers';
import {NewCustomer} from './new-customer/new-customer';

const routes: Routes = [
  { path: '', redirectTo: 'customers', pathMatch: 'full' },
  { path: 'customers', component: Customers },
  { path: 'accounts', component: Accounts },
  { path :"new-customer", component : NewCustomer },
  //{ path :"customer-accounts/:id", component : CustomerAccountsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
