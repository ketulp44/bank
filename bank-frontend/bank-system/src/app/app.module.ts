import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BankRegistrationComponent } from './bank-registration/bank-registration.component';
import { HttpClientModule } from '@angular/common/http';
import { OpenAccountComponent } from './open-account/open-account.component';
import { MakeTransactionComponent } from './make-transaction/make-transaction.component';
@NgModule({
  declarations: [
    AppComponent,
    BankRegistrationComponent,
    OpenAccountComponent,
    MakeTransactionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
