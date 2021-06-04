import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdCreateComponent } from './components/ad-create/ad-create.component';
import { AdDetailsComponent } from './components/ad-details/ad-details.component';
import { AdEditComponent } from './components/ad-edit/ad-edit.component';
import { AdListComponent } from './components/ad-list/ad-list.component';
import { AdminAdComponent } from './components/admin-ad/admin-ad.component';
import { AdminUserComponent } from './components/admin-user/admin-user.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { MyAdsComponent } from './components/my-ads/my-ads.component';
import { RegisterComponent } from './components/register/register.component';
import { TransactionReceivedComponent } from './components/transaction-received/transaction-received.component';
import { TransactionsComponent } from './components/transactions/transactions.component';
import { UserEditComponent } from './components/user-edit/user-edit.component';
import { AuthGuard } from './helpers/auth.guard';

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
  component: AdDetailsComponent,
  canActivate: [AuthGuard]
},
{
  path: 'publish',
  component: AdCreateComponent,
  canActivate: [AuthGuard]
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
  path: 'transactionsInitiated',
  component: TransactionsComponent,
  canActivate: [AuthGuard]
},
{
  path: 'transactionsReceived',
  component: TransactionReceivedComponent,
  canActivate: [AuthGuard]
},
{
  path: 'myAds',
  component: MyAdsComponent,
  canActivate: [AuthGuard]
},
{
  path: 'editAd/:adId',
  component: AdEditComponent,
  canActivate: [AuthGuard]
},
{
  path: 'editUser',
  component: UserEditComponent,
  canActivate: [AuthGuard]
},
{
  path: 'categories',
  component: CategoriesComponent
},
{
  path: 'favorites',
  component: AdListComponent
},
{
  path: 'adminAd',
  component: AdminAdComponent
},
{
  path: 'adminUser',
  component: AdminUserComponent
},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
