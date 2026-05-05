import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Accounts } from './accounts/accounts';
import { Customers } from './customers/customers';
import {NewCustomer} from './new-customer/new-customer';
import {Login} from './login/login';
import {AdminTemplate} from './admin-template/admin-template';
import {authentificationGuard} from './guards/authentication-guard';
import {authorizationGuard} from './guards/autorization-guard';
import {NotAuthorized} from './not-authorized/not-authorized';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'admin', component: AdminTemplate, canActivate: [authentificationGuard], children: [
      { path: 'customers', component: Customers },
      { path: 'accounts', component: Accounts },
      {
        path: 'new-customer',
        component: NewCustomer,
        canActivate: [authorizationGuard], //proteger la route avec ce guards
        data: { role: 'ADMIN' },
      },
      { path: 'not-authorized', component: NotAuthorized },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
