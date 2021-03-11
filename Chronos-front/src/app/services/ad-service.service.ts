import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ad, Category, CreateAd } from '../model/ad-interfaces';

@Injectable({
  providedIn: 'root'
})
export class AdServiceService {

  readonly url = 'http://localhost:8083';

  constructor(
    private http: HttpClient
  ) { }

  getAdById(id: number): Observable<Ad>{
    return this.http.get<Ad>(this.url + '/ad/' + id);
  }
  
  getAdsById(id: number): Observable<Ad[]>{
    return this.http.get<Ad[]>(this.url + '/ads/user/' + id);
  }

  filterAds(word?: string, categoryId?: number): Observable<Ad[]>{
    let params = '';
    if(word != null && categoryId !=null){
      params = '?word='+word+'&categoryId='+categoryId;
    }
    if(word!=null && categoryId == null){
      params = '?word='+word;
    }
    if(word==null && categoryId != null){
      params = '?categoryId='+categoryId;
    }
    return this.http.get<Ad[]>(this.url+'/search'+ params)
  }

  getCategories(): Observable<Category[]>{
    return this.http.get<Category[]>(this.url +'/categories')
  }

  postAd(body: CreateAd): Observable <Ad>{
    return this.http.post<Ad>(this.url+'/ad', body);
  }

  editAd(id: number, body: CreateAd): Observable <any>{
    return this.http.put(this.url+'/ad/'+ id, body);
  }

  deleteAd(id: number): Observable<any>{
    return this.http.delete(this.url+'/ad/' + id);
  }

}
