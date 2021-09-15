import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './site/login.component';
import { HomePageComponent } from './site/homePage.component';
import { RouterModule, Routes } from '@angular/router';
import { ShopComponent } from './site/shop.component';
import { AuthGuard } from './site/auth.guard';

const routes:Routes = [
  { path: '', component: HomePageComponent, canActivate: [AuthGuard], children: 
  [{ path: 'shop', component: ShopComponent}]},
  { path: 'login', component: LoginComponent}
  
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomePageComponent,
    ShopComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule, 
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
