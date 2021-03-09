import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user-interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  readonly url = 'http://localhost:8081';

  constructor(private http: HttpClient) { }


  getUserByid(id: number): Observable<User>{
    return this.http.get<User>(this.url+ '/user/'+ id)
  }




}
