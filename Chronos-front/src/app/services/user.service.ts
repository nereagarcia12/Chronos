import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateUser, User } from '../model/user-interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  readonly url = 'http://localhost:8083';

  constructor(private http: HttpClient) { }


  getUserByid(id: number): Observable<User>{
    return this.http.get<User>(this.url+ '/user/'+ id)
  }

  deleteUser(id: number): Observable<any>{
    return this.http.delete(this.url+'/user/' + id);
  }
}
