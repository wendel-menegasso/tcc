import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ContasBancarias } from '../model/contas-bancarias';
import { Observable, of } from 'rxjs';

@Injectable()
export class ContasBancariasService {

  private saveContasUrl: string;
  private findUrl: string;
  private deleteUrl: string;
  private alterarUrl: string;

  constructor(private http: HttpClient) {
    this.findUrl = 'http://localhost:9090/listarConta';
    this.saveContasUrl = 'http://localhost:9090/criarConta';
    this.deleteUrl = 'http://localhost:9090/deletarConta/';
    this.alterarUrl = 'http://localhost:9090/alterarConta';
  }

  public findAll(): Observable<ContasBancarias[]> {
    return this.http.get<ContasBancarias[]>(this.findUrl);
  }

  public save(contas: ContasBancarias) : Observable<ContasBancarias> {
    return this.http.post<ContasBancarias>(this.saveContasUrl, contas);
  }

  public delete(id: string) : Observable<any>{
    let headers = new HttpHeaders();
    headers.append("contas",btoa("id:"+id));
    return this.http.delete<any>(this.deleteUrl+id, { headers: headers });
  }
  public alterar(conta: ContasBancarias) : Observable<ContasBancarias> {
    return this.http.put<ContasBancarias>(this.alterarUrl, conta);
  }
}
