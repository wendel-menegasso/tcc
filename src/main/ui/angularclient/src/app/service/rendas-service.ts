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
  private url: string;
  private gerarRelatorioRenda: string;

  constructor(private http: HttpClient) {
    this.findUrl = 'http://localhost:9090/listarRenda';
    this.saveRendasUrl = 'http://localhost:9090/criarRenda';
    this.deleteUrl = 'http://localhost:9090/deletarRenda/';
    this.recebeDadosAlterarRendasUrl = 'http://localhost:9090/recebeDadosAlterarRenda';
    this.alterarUrl = 'http://localhost:9090/alterarRenda';
    this.gerarRelatorioRenda = 'http://localhost:9090/gerarRelatorioRenda';
    //this.findUrl = 'http://20.124.3.145:9090/listarRenda';
    //this.saveRendasUrl = 'http://20.124.3.145:9090/criarRenda';
    //this.deleteUrl = 'http://20.124.3.145:9090/deletarRenda/';
    //this.recebeDadosAlterarRendasUrl = 'http://20.124.3.145:9090/recebeDadosAlterarRenda';
    //this.alterarUrl = 'http://20.124.3.145:9090/alterarRenda';
  }

  public findAll(req: string): Observable<Rendas[]> {
    return this.http.post<Rendas[]>(this.findUrl, req);
  }

  public gerarRelatorio(rendas: Rendas): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post(this.gerarRelatorioRenda, rendas, { responseType: 'blob', headers });
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
