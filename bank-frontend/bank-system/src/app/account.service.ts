import { Injectable } from '@angular/core';
import { Account } from './model/account';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transaction } from './model/transaction';
import { Bank } from './model/bank';
import { REQUEST_URL, IMAGE_UPLOAD_URL } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class AccountService {
  

  constructor(private http: HttpClient) { }
  registerAccount(account: Account): Observable<Account> {
    return this.http.post<Account>(`${REQUEST_URL}/bank/account`, account);
  }
  makeTransaction(account: Transaction): Observable<Transaction> {
    return this.http.post<Transaction>(`${REQUEST_URL}/bank/maketransaction`, account);
  }
  getBanks(): Observable<Bank[]> {
    return this.http.get<Bank[]>(`${REQUEST_URL}/bank/banks`);
  }

  getTransactions(accountNo: number,page: number):Observable<any>{
    let url = `${REQUEST_URL}/bank/transactions/${accountNo}/${page}`;
    return this.http.get<any>(url);
  }
  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    const req = new HttpRequest('POST', `${IMAGE_UPLOAD_URL}`, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }


}
