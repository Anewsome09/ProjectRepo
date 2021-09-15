import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Product } from '../product';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private baseUrl: string = "http://localhost:8081/products"

  constructor(private http: HttpClient) { }

  addProduct(product: Product): Observable<Product> {
    console.log(product);
    return this.http.post<Product>(this.baseUrl, product, this.httpOptions)
  }
}
