import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';
import { Product } from '../product';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap, distinct, filter } from 'rxjs/operators';
import { Output, EventEmitter } from '@angular/core';
import { map } from 'rxjs/operators';
import { of, from } from 'rxjs';

@Component({
  selector: 'app-search-category',
  templateUrl: './search-category.component.html',
  styleUrls: ['./search-category.component.css']
})
export class SearchCategoryComponent implements OnInit {

  categories$!: Observable<string[]>;

  products: Product[] = [];

  testSelection = []

  testTerm = "furniture";
  
  private searchTerms = new Subject<string>();
  

  @Output() newCategoryEvent = new EventEmitter<string>();

  
  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.categories$ = this.searchTerms.pipe(
      
      debounceTime(300),
      // ignore new term if same as previous term
      distinctUntilChanged(),
      // switch to new search observable each time the term changes
      switchMap((term: string) => from(this.productService.searchCategory(term)).pipe(
        //distinct( results => results.filter( r => r.category == term) )
        
      ))
    )
    //console.log(this.products$)
  }

  

  search(term: string): void {
    this.searchTerms.next(term);
  }
  
selectCategory(term: string): void {
  this.searchTerms.next(term);
  this.newCategoryEvent.emit(term)
}

}
