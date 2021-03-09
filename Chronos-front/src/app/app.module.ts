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
import {ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    AdCardComponent,
    AdDetailsComponent,
    AdListComponent,
    HeaderComponent,
    FooterComponent,
    AdCreateComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
