import { Component, OnInit } from '@angular/core';
import { Transaction } from '../model/transaction';
import { FormGroup, FormControl } from '@angular/forms';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-passbook',
  templateUrl: './passbook.component.html',
  styleUrls: ['./passbook.component.css']
})
export class PassbookComponent implements OnInit {
transactions: Transaction []= [];
totalRecord:number;
pageIndex:number=0;
form: FormGroup = new FormGroup({
  accountNo: new FormControl('')
});
  constructor(private accountService:AccountService) { }
  
  ngOnInit() {
  }
  get  accountNo():FormControl{
    return <FormControl>this.form.controls.accountNo;
  }
  onSubmit(){    
    let page:number =0;
    this.getTranactions(this.form.controls.accountNo.value,this.pageIndex);
    
  }
  getTranactions(accountNo:number,page:number){
    this.accountService.getTransactions(this.accountNo.value,page).subscribe((data)=>{
      this.transactions = data.content;
      this.totalRecord = data.totalElements;
    });
  }
  paginate(event){
    this.pageIndex=event.page;
    this.getTranactions(this.accountNo.value,this.pageIndex);
  }


}
