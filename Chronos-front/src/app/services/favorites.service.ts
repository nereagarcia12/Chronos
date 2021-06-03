import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Favorite } from '../model/favorite-interface';

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {

  readonly url = 'http://localhost:8083';

  constructor( private http: HttpClient) { 
  }

getFavoriteByUser(userId : number): Observable<Favorite[]>{
return this.http.get<Favorite[]>(this.url + '/favorite/' + userId)
}

saveFavorite(body: Favorite): Observable<any>{
  return this.http.post(this.url + '/favorite/', body);
  }

  deleteFavorite(adId:number , userId : number): Observable<any>{
    return this.http.delete(this.url+'/favorites/'+ adId+'/'+userId);
  }

}
