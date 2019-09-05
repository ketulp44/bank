import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BankRegistrationComponent } from './bank-registration/bank-registration.component';
import { MakeTransactionComponent } from './make-transaction/make-transaction.component';
import { PassbookComponent } from './passbook/passbook.component';
import { SuccessfulComponent } from './successful/successful.component';


const routes: Routes = [
{ path: 'openaccount', component: BankRegistrationComponent },
{ path: 'maketransaction', component: MakeTransactionComponent },
{ path: 'passbook', component: PassbookComponent },
{ path: 'success', component: SuccessfulComponent },
{ path: '', component: BankRegistrationComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
