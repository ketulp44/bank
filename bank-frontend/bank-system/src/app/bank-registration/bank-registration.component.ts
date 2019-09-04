import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AccountService } from '../account.service';
import { Account } from '../model/account';
import { Bank } from '../model/bank';

@Component({
  selector: 'app-bank-registration',
  templateUrl: './bank-registration.component.html',
  styleUrls: ['./bank-registration.component.css']
})
export class BankRegistrationComponent implements OnInit {
  banks: Bank [] = []
  registrationForm = new FormGroup({
    name: new FormControl('',[Validators.required]),
    phone: new FormControl('', [Validators.required ]),
    email: new FormControl('', [Validators.required,Validators.email]),
    address: new FormControl('', [Validators.required]),
    accountType: new FormControl('', [Validators.required]),
    birthDay: new FormControl('', [Validators.required]),
    panNo: new FormControl(''),
    bankName: new FormControl('',[Validators.required])
  });
  constructor(private accountService:AccountService) { }
  
  ngOnInit() {
    this.getBanks();  
  }
get name(): FormControl{
  return <FormControl>this.registrationForm.controls.name;
}
get phone(): FormControl{
  return <FormControl>this.registrationForm.controls.phone;
}
get address(): FormControl{
  return <FormControl>this.registrationForm.controls.address;
}
get accountType(): FormControl{
  return <FormControl>this.registrationForm.controls.accountType;
}
get birthDay(): FormControl{
  return <FormControl>this.registrationForm.controls.birthDay;
}
get email(): FormControl{
  
  return <FormControl>this.registrationForm.controls.email;
}
get panNo(): FormControl{  
  return <FormControl>this.registrationForm.controls.panNo;
}
get bankName(): FormControl{
  return <FormControl>this.registrationForm.controls.bankName;
}
getBanks(){
  this.accountService.getBanks().subscribe((data)=>{
    this.banks = data;
  });
}
register(event){
  console.log("register");
  console.log(this.accountType.value);
  
  let account:Account = {
    bankId: this.bankName.value,
    name: this.name.value,
    email: this.email.value,
    phone: this.phone.value,
    panNo: this.panNo.value,
    address: this.address.value,
    birthDate: this.birthDay.value,
    accountType: this.accountType.value
  }
  console.log(account);
  if(this.registrationForm.valid){
    this.accountService.registerAccount(account).subscribe(()=>{
      console.log("saved");
      
    });
  }
  
  
}


onChangeAccountType(){
  
  if(this.accountType.value == "saving"){
      console.log("saving");
      this.panNo.setValidators(null);
      console.log(this.panNo);
      
  }
  else{
    this.panNo.setValidators([Validators.required]);
    console.log(this.panNo);
    
  }
  this.panNo.reset();
}
}
