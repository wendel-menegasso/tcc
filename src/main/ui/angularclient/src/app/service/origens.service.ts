import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Origem } from '../model/origem';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class OrigensService {

  private carregarOrigemUrl: string;

  constructor(private http: HttpClient) {
    //this.carregarOrigemUrl = 'http://localhost:9090/carregarOrigem';
    this.carregarOrigemUrl = 'http://20.124.3.145:9090/carregarOrigem';
  }

  public findAll(user: string): Observable<Origem[]>{
    return this.http.post<Origem[]>(this.carregarOrigemUrl, user);
  }

}
