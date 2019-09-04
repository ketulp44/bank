import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BankRegistrationComponent } from './bank-registration/bank-registration.component';
import { MakeTransactionComponent } from './make-transaction/make-transaction.component';


const routes: Routes = [{ path: 'openaccount', component: BankRegistrationComponent },
{ path: 'maketransaction', component: MakeTransactionComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
