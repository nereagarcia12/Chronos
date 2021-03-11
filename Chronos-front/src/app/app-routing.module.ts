import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdCreateComponent } from './components/ad-create/ad-create.component';
import { AdDetailsComponent } from './components/ad-details/ad-details.component';
import { AdEditComponent } from './components/ad-edit/ad-edit.component';
import { AdListComponent } from './components/ad-list/ad-list.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MyAdsComponent } from './components/my-ads/my-ads.component';
import { RegisterComponent } from './components/register/register.component';
import { TransactionsComponent } from './components/transactions/transactions.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';

const routes: Routes = [
  {
  path: '',
  component: HomeComponent
},
{
  path: 'listing/:word',
  component: AdListComponent
},
{
  path: 'categorySearch/:category',
  component: AdListComponent
},
{
  path: 'listing',
  component: AdListComponent
},
{
  path: 'details/:adId',
  component: AdDetailsComponent
},
{
  path: 'publish',
  component: AdCreateComponent
},
{
  path: 'login',
  component: LoginComponent
},
{
  path: 'register',
  component: RegisterComponent
},
{
  path: 'transactions',
  component: TransactionsComponent
},
{
  path: 'myAds',
  component: MyAdsComponent
},
{
  path: 'editAd/:adId',
  component: AdEditComponent
},
{
  path: 'editUser/:userId',
  component: UserEditComponent
},
{
  path: 'categories',
  component: CategoriesComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
