import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Rendas } from '../model/rendas';

@Injectable()
export class RendasService {


  private saveRendasUrl: string;
  private findUrl: string;
  private deleteUrl: string;
  private recebeDadosAlterarRendasUrl: string;
  private alterarUrl: string;

  constructor(private http: HttpClient) {
    this.findUrl = 'http://localhost:9090/listarRendas';
    this.saveRendasUrl = 'http://localhost:9090/criarRendas';
    this.deleteUrl = 'http://localhost:9090/deletarRendas/';
    this.recebeDadosAlterarRendasUrl = 'http://localhost:9090/recebeDadosAlterarRendas';
    this.alterarUrl = 'http://localhost:9090/alterarRendas';
  }

  public findAll(): Observable<Rendas[]> {
    return this.http.get<Rendas[]>(this.findUrl);
  }

  public save(rendas: Rendas) : Observable<Rendas> {
    return this.http.post<Rendas>(this.saveRendasUrl, rendas);
  }

  public delete(id: string) : Observable<any>{
    let headers = new HttpHeaders();
    headers.append("rendas",btoa("id:"+id));
    return this.http.delete<any>(this.deleteUrl+id, { headers: headers });
  }

  public recebeDadosAlterarRendas(rendas: Rendas) : Observable<Rendas> {
    return this.http.post<Rendas>(this.recebeDadosAlterarRendasUrl, rendas);
  }

  public alterarRendas(rendas: Rendas) : Observable<Rendas>{
    return this.http.put<Rendas>(this.alterarUrl, rendas);
  }
}
