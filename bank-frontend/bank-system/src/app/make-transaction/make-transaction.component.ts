import { Component, OnInit, ViewChild, ElementRef, TemplateRef } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AccountService } from '../account.service';
import { Transaction } from '../model/transaction';

@Component({
  selector: 'app-make-transaction',
  templateUrl: './make-transaction.component.html',
  styleUrls: ['./make-transaction.component.css']
})
export class MakeTransactionComponent implements OnInit {
  max = 0;

  tranForm = new FormGroup({
    accountType: new FormControl('', [Validators.required]),
    transactionType: new FormControl('', [Validators.required]),
    transactionVia: new FormControl('', [Validators.required]),
    accountNo: new FormControl('', [Validators.required]),
    amount: new FormControl('', [Validators.required])
  });
  creditOptions = ["atm", "check", "slip"];
  debitOptions = ["atm", "slip", "online"];
  options = [];
  enablePan = false;
  constructor(private accountService: AccountService) { }

  ngOnInit() {
  }
  get transactionType(): FormControl {
    return <FormControl>this.tranForm.controls.transactionType;
  }
  get accountType(): FormControl {
    return <FormControl>this.tranForm.controls.accountType;
  }
  get transactionVia(): FormControl {
    return <FormControl>this.tranForm.controls.transactionVia;
  }
  get accountNo(): FormControl {
    return <FormControl>this.tranForm.controls.accountNo;
  }
  get amount(): FormControl {
    return <FormControl>this.tranForm.controls.amount;
  }
  get panNo(): FormControl {
    return <FormControl>this.tranForm.controls.panNo;
  }
  onChangeTransactionType() {
    console.log("onChangeTransactionType");

    if (this.transactionType.value == "credit") {
      this.options = this.creditOptions;
    }
    if (this.transactionType.value == "debit") {
      this.options = this.debitOptions;
    }
  }
  onChangeAccountType() {
    this.amount.reset();
    this.enablePan = false;
    if (this.panNo) {
      this.panNo.reset();
    }

    if (this.accountType.value == "current") {
      this.tranForm.removeControl('panNO');
    }
  }
  getFieldsValue(): Transaction {
    let transaction: Transaction = {
      transactionVia: this.transactionVia.value,
      transactionType: this.transactionType.value,
      amount: this.amount.value,
      accountNo: this.accountNo.value,
      accountType: this.accountType.value
    };
    if (transaction.accountType == "saving" && transaction.amount > 50000) {
      transaction.panNo = this.panNo.value;
    }
    return transaction;
  }
  makeTransaction() {
    let transaction = this.getFieldsValue();
    if (this.tranForm.valid) {
      this.accountService.makeTransaction(transaction).subscribe((data) => {
        console.log(data);
      });
    }
  }
  onFocusOutAmount() {
    if (this.amount.value > 50000 && this.accountType.value == "saving") {
      this.tranForm.addControl('panNo', new FormControl('', [Validators.required]));
      this.enablePan = true;
    }
    if (this.amount.value < 5000 && this.accountType.value == "saving") {
      this.enablePan = false;
      this.tranForm.removeControl('panNo');
    }
  }

}
