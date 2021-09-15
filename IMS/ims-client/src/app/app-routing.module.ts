import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SubmitProductComponent } from './submit-product/submit-product.component';

import { ProductsComponent } from './products/products.component';
import { TransactionsComponent } from './transactions/transactions.component';
import { RestockComponent } from './restock/restock.component';

const routes: Routes = [
  {path: 'submitProduct', component: SubmitProductComponent },
  {path: 'products', component:ProductsComponent},
  {path: 'transactions', component:TransactionsComponent},
  {path: 'restock', component: RestockComponent}
  //{path: 'products/:category', component:ProductsComponent} /*might have to make this a seperate component*/
//actually will have to make this a seperate component... must do that later ... unless its the same thing but switched but the paths are different ahhh
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
