import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Imovel } from '../model/imovel';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImoveisService {

  private saveImoveisUrl: string;
  private findUrl: string;
  private deleteUrl: string;
  private recebeDadosAlterarImoveisUrl: string;
  private alterarUrl: string;
  private gerarRelatorioImoveis: string;

  constructor(private http: HttpClient) { 
    this.findUrl = 'http://localhost:9090/listarImovel';
    this.saveImoveisUrl = 'http://localhost:9090/criarImovel';
    this.deleteUrl = 'http://localhost:9090/deletarImovel/';
    this.recebeDadosAlterarImoveisUrl = 'http://localhost:9090/recebeDadosAlterarImovel';
    this.alterarUrl = 'http://localhost:9090/alterarImovel';
    this.gerarRelatorioImoveis = 'http://localhost:9090/gerarRelatorioImovel';
  }

  public findAll(user: string): Observable<Imovel[]> {
    return this.http.post<Imovel[]>(this.findUrl, user);
  }

  public gerarRelatorio(imovel: Imovel): Observable<Blob> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post(this.gerarRelatorioImoveis, imovel, { responseType: 'blob', headers });
  }

  public save(imovel: Imovel) : Observable<Imovel> {
    return this.http.post<Imovel>(this.saveImoveisUrl, imovel);
  }

  public delete(id: string) : Observable<any>{
    let headers = new HttpHeaders();
    headers.append("imovel",btoa("id:"+id));
    return this.http.delete<any>(this.deleteUrl+id, { headers: headers });
  }

  public recebeDadosAlterarImoveis(imovel: Imovel) : Observable<Imovel> {
    return this.http.post<Imovel>(this.recebeDadosAlterarImoveisUrl, imovel);
  }

  public alterarImoveis(imovel: Imovel) : Observable<Imovel>{
    return this.http.put<Imovel>(this.alterarUrl, imovel);
  }

}
