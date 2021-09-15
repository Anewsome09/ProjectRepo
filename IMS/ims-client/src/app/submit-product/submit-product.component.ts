import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Subject } from 'rxjs';
import { Product } from '../product';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-submit-product',
  templateUrl: './submit-product.component.html',
  styleUrls: ['./submit-product.component.css']
})
export class SubmitProductComponent implements OnInit {

  products: Product[] = [];
  errors = "";
  success = "";

  productModel: Product = {
    productId: 0,
    title: '',
    category:  '',
    man:  '',
    minLimit:  0,
    productQuantity: 0,

    quantity: 0,
    vendor:  '',
    batchCode:  '',
    invoiceNumber:  ''
  };

  constructor(
    private productService: ProductService
    ) { }

  ngOnInit(): void {
  }

  // adding an entirely new product
  // [todo] add quantity column
  // [todo] add() should count as an 'IN' transaction in the product_stock table
  add(newProduct: Product): void {
    console.log(this.productModel.minLimit);
    console.log(newProduct);
    this.productService.addProduct(newProduct)
    .subscribe(product => {
      console.log('Product submitted', product); // this should return the product id
      this.products.push(product);
      this.errors = "Something went wrong.";
      this.success = "Success!"
    },
      error => {
        this.errors = error;
      }
    );
  }

  onSubmit(productForm: NgForm): void {
    // onSubmit() calls add() which calls addProduct() from the ProductService
    this.add(this.productModel);
    productForm.reset();
    
  }

}
