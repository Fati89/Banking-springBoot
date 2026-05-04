import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { Customers } from './customers/customers';
import { Accounts } from './accounts/accounts';
import { Navbar } from './navbar/navbar';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { NewCustomer } from './new-customer/new-customer';

@NgModule({
  declarations: [App, Customers, Accounts, Navbar, NewCustomer],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, ReactiveFormsModule],
  providers: [],
  bootstrap: [App],
})
export class AppModule {}
