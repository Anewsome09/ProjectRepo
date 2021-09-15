import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ProductsComponent } from './products/products.component';

import { SubmitProductComponent } from './submit-product/submit-product.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
// import { StocksComponent } from './stocks/stocks.component';
import { SearchCategoryComponent } from './search-category/search-category.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { RestockComponent } from './restock/restock.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProductsComponent,
    SubmitProductComponent,
    SearchCategoryComponent,
    TransactionsComponent,
    RestockComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
