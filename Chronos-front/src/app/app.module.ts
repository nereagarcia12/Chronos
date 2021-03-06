import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdCardComponent } from './components/ad-card/ad-card.component';
import { AdDetailsComponent } from './components/ad-details/ad-details.component';
import { AdListComponent } from './components/ad-list/ad-list.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { AdCreateComponent } from './components/ad-create/ad-create.component';
import {FormsModule, ReactiveFormsModule } from '@angular/forms';
import { authInterceptorProviders } from './helpers/auth.interceptor';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { HomeComponent } from './components/home/home.component';
import { TransactionsComponent } from './components/transactions/transactions.component';
import { MyAdsComponent } from './components/my-ads/my-ads.component';
import { AdEditComponent } from './components/ad-edit/ad-edit.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { ProfilesidebarComponent } from './components/profilesidebar/profilesidebar.component';
import { TransactionReceivedComponent } from './components/transaction-received/transaction-received.component';
import { JwPaginationModule } from 'jw-angular-pagination';
import { PaginatorComponent } from './components/paginator/paginator.component';
import { AdminUserComponent } from './components/admin-user/admin-user.component';
import { AdminAdComponent } from './components/admin-ad/admin-ad.component';


@NgModule({
  declarations: [
    AppComponent,
    AdCardComponent,
    AdDetailsComponent,
    AdListComponent,
    HeaderComponent,
    FooterComponent,
    AdCreateComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    TransactionsComponent,
    MyAdsComponent,
    AdEditComponent,
    UserEditComponent,
    CategoriesComponent,
    ProfilesidebarComponent,
    TransactionReceivedComponent,
    PaginatorComponent,
    AdminUserComponent,
    AdminAdComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    JwPaginationModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
