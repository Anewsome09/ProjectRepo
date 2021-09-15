import { Injectable } from '@angular/core';
import { Product } from './product';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import {  tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class ProductService {
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private baseUrl = "http://localhost:8081/products"

  getAllProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}`);
  }

  getProductByCategory(category: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.baseUrl}/${category}`)
  }

searchCategory(term: string): Observable<string[]> {
  if (!term.trim()) {
    return of([]);
  }
  return this.http.get<string[]>(`${this.baseUrl}/ctgry/${term}`).pipe(
    
    tap(x => x.length ?
       console.log(`found categories matching "${term}"`) :
       console.log(`no categories matching "${term}"`)),
    tap(x => console.log(x))
  );
}

constructor(
  private http: HttpClient) { }
}

