import { Injectable } from '@angular/core';
import { Product } from './product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import {  tap } from 'rxjs/operators';
import { Transaction } from './transactions';


@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private baseUrl = "http://localhost:8081/transactions"

  getAllTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.baseUrl}`);
  }

  
constructor(
  private http: HttpClient) { }
}


