import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdCreateComponent } from './components/ad-create/ad-create.component';
import { AdDetailsComponent } from './components/ad-details/ad-details.component';
import { AdListComponent } from './components/ad-list/ad-list.component';

const routes: Routes = [
  {
  path: '',
  component: AdListComponent
},
{
  path: 'details/:adId',
  component: AdDetailsComponent
},
{
  path: 'publish',
  component: AdCreateComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
