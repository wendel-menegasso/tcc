import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Gastos } from '../model/gastos';

@Injectable({
  providedIn: 'root'
})
export class GastosService {

  private saveGastosUrl: string;
  private findUrl: string;
  private deleteUrl: string;
  private recebeDadosAlterarGastosUrl: string;
  private alterarUrl: string;

  constructor(private http: HttpClient) {
    this.findUrl = 'http://localhost:9090/listarGasto';
    this.saveGastosUrl = 'http://localhost:9090/criarGasto';
    this.deleteUrl = 'http://localhost:9090/deletarGasto/';
    this.recebeDadosAlterarGastosUrl = 'http://localhost:9090/recebeDadosAlterarGasto';
    this.alterarUrl = 'http://localhost:9090/alterarGasto';
  }

  public findAll(user: string): Observable<Gastos[]> {
    return this.http.post<Gastos[]>(this.findUrl, user);
  }

  public save(gastos: Gastos) : Observable<Gastos> {
    return this.http.post<Gastos>(this.saveGastosUrl, gastos);
  }

  public delete(id: string) : Observable<any>{
    let headers = new HttpHeaders();
    headers.append("gastos",btoa("id:"+id));
    return this.http.delete<any>(this.deleteUrl+id, { headers: headers });
  }

  public recebeDadosAlterarRendas(gastos: Gastos) : Observable<Gastos> {
    return this.http.post<Gastos>(this.recebeDadosAlterarGastosUrl, gastos);
  }

  public alterarRendas(gastos: Gastos) : Observable<Gastos>{
    return this.http.put<Gastos>(this.alterarUrl, gastos);
  }
}