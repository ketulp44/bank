import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, ValidatorFn, ValidationErrors } from '@angular/forms';
import { AccountService } from '../account.service';
import { Transaction } from '../model/transaction';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { error } from 'util';
import { Router } from '@angular/router';
@Component({
  selector: 'app-make-transaction',
  templateUrl: './make-transaction.component.html',
  styleUrls: ['./make-transaction.component.css']
})
export class MakeTransactionComponent implements OnInit {
  max = 0;
  fileSizeExceed = false;
  successful: boolean = false;
  error: boolean = false;
  errorMessage = '';
  tranForm = new FormGroup({
    accountType: new FormControl('', [Validators.required]),
    transactionType: new FormControl('', [Validators.required]),
    transactionVia: new FormControl('', [Validators.required]),
    accountNo: new FormControl('', [Validators.required]),
    amount: new FormControl('', [Validators.required])
  });
  transaction: Transaction = new Transaction();
  creditOptions = ["atm", "check", "slip"];
  debitOptions = ["atm", "slip", "online"];
  options = [];
  enablePan = false;
  progress: { percentage: number } = { percentage: 0 }
  selectedFiles: FileList;
  currentFileUpload: File;
  currentFileUploadName: String = 'No File Chosen';
  isUploaded: boolean;
  constructor(private accountService: AccountService, private router: Router) { }

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
  get panImg(): FormControl {
    return <FormControl>this.tranForm.controls.panImg;
  }

  isBlank(value: String): boolean {
    return (value == '' || value == null || value == undefined)
  }

  panValidator(control: FormGroup): ValidationErrors | null {
    const no = control.get('panNo');
    const img = control.get('panImg');

    if (no || img) {
      if (no && this.isBlank(no.value) && !this.isUploaded) {
        return { 'requiredOneOfPan': true }
      }
    }
    return null;
  }

  onChangeTransactionType() {

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
      this.panImg.reset();
    }

    if (this.accountType.value == "current") {
      this.tranForm.removeControl('panNo');
      this.tranForm.removeControl('panImg');
      this.tranForm.setValidators([]);
    }
  }
  setFieldsValue() {

    this.transaction.transactionVia = this.transactionVia.value,
      this.transaction.transactionType = this.transactionType.value,
      this.transaction.amount = this.amount.value,
      this.transaction.accountNo = this.accountNo.value,
      this.transaction.accountType = this.accountType.value

    if (this.transaction.accountType == "saving" && this.transaction.amount > 50000) {
      this.transaction.panNo = this.panNo.value;
    }
  }
  makeTransaction() {


    this.setFieldsValue();
    if (this.tranForm.valid) {
      this.accountService.makeTransaction(this.transaction).pipe(catchError(this.handleError.bind(this))).subscribe(() => {
        this.router.navigate(['success']);
      });
    }
  }
  onFocusOutAmount() {
    if (this.amount.value > 50000 && this.accountType.value == "saving") {
      this.tranForm.addControl('panNo', new FormControl(''));
      this.tranForm.addControl('panImg', new FormControl(''));
      this.tranForm.setValidators(this.panValidator.bind(this));
      this.enablePan = true;
    }
    if (this.amount.value < 5000 && this.accountType.value == "saving") {
      this.enablePan = false;
      this.tranForm.removeControl('panNo');
      this.tranForm.removeControl('panImg');
      this.tranForm.setValidators([]);
    }
  }

  handleError(error) {    
    if (error.error) {
      this.errorMessage = error.error.message;
    }
    this.error = true;
    return throwError(this.errorMessage);
  }
  selectFile(event) {
    this.progress.percentage = 0;
    const file = event.target.files.item(0);
    if ((file.type.match('image.*')) && (file.size < 2000000)) {
      this.selectedFiles = event.target.files;
      this.currentFileUpload = this.selectedFiles.item(0);
      this.currentFileUploadName = this.currentFileUpload.name;
    } else {
      this.fileSizeExceed = true;      
    }
  }
  onUpload() {
    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0);
    this.accountService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        if (event.body == "FAIL") {
          console.log('fail');
        }
        else {
          this.isUploaded = true;          
          this.tranForm.setErrors(null);
          this.transaction.panImgUrl = event.body.toString();
        }
      }
    });
  }

}
