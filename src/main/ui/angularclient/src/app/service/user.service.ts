import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable, of } from 'rxjs'

@Injectable()
export class UserService {

  private authenticateUrl: string;
  private usersUrl: string;

  constructor(private http: HttpClient) {
      this.usersUrl = 'http://localhost:9090/users';
      this.authenticateUrl = 'http://localhost:9090/authenticate';
      //this.usersUrl = 'http://20.124.3.145:9090/users';
      //this.authenticateUrl = 'http://20.124.3.145:9090/authenticate';
  }

  public findAll(): Observable<User[]> {
    return this.http.get<User[]>(this.usersUrl);
  }

  public authenticate(user: User) : Observable<User> {
    return this.http.post<User>(this.authenticateUrl, user);
  }

}
