import { Injectable } from '@angular/core';
import { Account } from './model/account';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from './model/transaction';
import { Bank } from './model/bank';
@Injectable({
  providedIn: 'root'
})
export class AccountService {
  baseUrl = "http://localhost:8080/bank/"
  constructor(private http: HttpClient) { }
  registerAccount(account: Account): Observable<Account> {
    return this.http.post<Account>(this.baseUrl + 'account', account);
  }
  makeTransaction(account: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(this.baseUrl + 'maketransaction', account);
  }
  getBanks(): Observable<Bank[]> {
    return this.http.get<Bank[]>(this.baseUrl + 'banks');
  }

}
