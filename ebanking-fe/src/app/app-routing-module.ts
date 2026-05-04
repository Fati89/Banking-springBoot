import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Accounts } from './accounts/accounts';
import { Customers } from './customers/customers';

const routes: Routes = [
{
    path: '',
    redirectTo: 'customers',
    pathMatch: 'full'
  },
  {
    path: 'customers',
    component: Customers
  },
  {
    path: 'accounts',
    component: Accounts
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
