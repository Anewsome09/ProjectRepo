import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from './product';
import { Stocks } from './stocks';

@Injectable({
  providedIn: 'root'
})
export class StocksService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private baseUrl = "http://localhost:8081/transactions"

  constructor(
    private http: HttpClient) { }

  updateProductStock(stock: Stocks): Observable<Product> {
    return this.http.post<Product>(this.baseUrl, stock, this.httpOptions);
  }
  
  // addTicket(ticket: Ticket): Observable<Ticket> {
  //   return this.http.post<Ticket>(this.baseUrl, ticket, this.httpOptions);
  // }
}
