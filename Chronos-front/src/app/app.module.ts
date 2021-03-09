import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdCardComponent } from './components/ad-card/ad-card.component';
import { AdDetailsComponent } from './components/ad-details/ad-details.component';
import { AdListComponent } from './components/ad-list/ad-list.component';


@NgModule({
  declarations: [
    AppComponent,
    AdCardComponent,
    AdDetailsComponent,
    AdListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
