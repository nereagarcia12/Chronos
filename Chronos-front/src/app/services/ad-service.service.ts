import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ad, Category } from '../model/ad-interfaces';

@Injectable({
  providedIn: 'root'
})
export class AdServiceService {

  readonly url = 'http://localhost:8080';

  constructor(
    private http: HttpClient
  ) { }

  getAdById(id: number): Observable<Ad>{
    return this.http.get<Ad>(this.url + '/ad/' + id);
  }

  filterAds(word?: string, categoryId?: number): Observable<Ad[]>{
    let params = '';
    if(word != null && categoryId !=null){
      params = '?word='+word+'&categoryId='+categoryId;
    }
    return this.http.get<Ad[]>(this.url+'/search'+ params)
  }

  getCategories(): Observable<Category[]>{
    return this.http.get<Category[]>(this.url +'/categories')
  }
}
