import { Component, OnInit } from '@angular/core';

import { ProductService } from '../product.service';
import { Product } from '../product';
import { Location } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Output, EventEmitter } from '@angular/core';
import { StocksService } from '../stocks.service';
import { Stocks } from '../stocks';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Product[] = [];
  productByCategory: Product[] = [];
  selectedCategory: string = "";
  invoiceNumber: number = 0;
  errorMessage: string = "";
  
  categorySelection(term: string) {
    this.selectedCategory = term;
    this.getCategory();
  }

  constructor(private productService: ProductService, 
              private route: ActivatedRoute,
              private location: Location,
              private stocksService: StocksService) { }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts() {
    this.productService.getAllProducts()
      .subscribe(products => {
        this.productByCategory = products;
      })
  }

  getCategory() {
    console.log(this.selectedCategory)
    let category = this.selectedCategory
    if (category == "") {
      this.productByCategory = this.products;
    } else {
      this.productService.getProductByCategory(category)
      .subscribe(filteredProducts => {
        this.productByCategory = filteredProducts;
      })
    }
  }

  updateProductStock(product: Product, transactionType: string): void {
    let transaction:Stocks = {
      stockId: null,
      productId: product.productId,
      transactionDate: new Date(),
      vendor: product.vendor,
      batchCode: product.batchCode,
      invoiceNum: product.invoiceNumber,
      quantity: product.quantity,
      transactionType: transactionType
    };

    if (transaction.transactionType == "OUT" && (transaction.quantity > product.productQuantity || product.productQuantity < product.minLimit)) {
      this.errorMessage = "Error: Insufficient quantity!"
    } else {
      this.errorMessage = "";
      this.stocksService.updateProductStock(transaction)
        .subscribe(p => {
          this.productByCategory.forEach(pr => {
            if (pr.productId == p.productId) {
              pr.productQuantity = p.productQuantity;
              pr.quantity = 0;
              pr.batchCode = "";
              pr.invoiceNumber = "";
              pr.vendor = "";
            }
          })
        });
    }
  }


}
