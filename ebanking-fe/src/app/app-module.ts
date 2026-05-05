import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Customers } from './customers/customers';
import { Accounts } from './accounts/accounts';
import { Navbar } from './navbar/navbar';
import {
  HTTP_INTERCEPTORS,
  HttpClientModule,
  HttpInterceptorFn,
  provideHttpClient,
  withInterceptors,
} from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { NewCustomer } from './new-customer/new-customer';
import { Login } from './login/login';
import { AdminTemplate } from './admin-template/admin-template';
import { provideRouter } from '@angular/router';
import { appHttpInterceptor } from './interceptors/app-http-interceptor';
import { routes } from './app-routing-module';
import { NotAuthorized } from './not-authorized/not-authorized';

@NgModule({
  declarations: [
    App,
    Customers,
    Accounts,
    Navbar,
    NewCustomer,
    Login,
    AdminTemplate,
    NotAuthorized,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, ReactiveFormsModule],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([appHttpInterceptor]), //pour utilser l'intercepteur(il faut le mettre en dedant de clienthttp)
    ), //pour utilser le client http dans n'importe quelle service il faut faire @injection des
  ],
  bootstrap: [App],
})
export class AppModule {}
