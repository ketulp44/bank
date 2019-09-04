import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'bank-system';
  activePage="createAccount";
  makeATransaction(){
      this.activePage="makeATransaction";
  }
  onClickCreateAccount(){
    this.activePage="createAccount";
  }
}
