import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ContasBancarias } from '../model/contas-bancarias';
import { Observable, of } from 'rxjs';

@Injectable()
export class ContasBancariasService {

  private saveContasUrl: string;
  private findUrl: string;
  private deleteUrl: string;
  private recebeDadosAlterarContaUrl: string;
  private alterarUrl: string;
  private gerarRelatorioConta: string;

  constructor(private http: HttpClient) {
    
      this.findUrl = 'http://localhost:9090/listarConta';
      this.saveContasUrl = 'http://localhost:9090/criarConta';
      this.deleteUrl = 'http://localhost:9090/deletarConta/';
      this.recebeDadosAlterarContaUrl = 'http://localhost:9090/recebeDadosAlterarConta';
      this.alterarUrl = 'http://localhost:9090/alterarConta';
      this.gerarRelatorioConta = 'http://localhost:9090/gerarRelatorioConta';

      //this.findUrl = 'http://20.124.3.145:9090/listarConta';
      //this.saveContasUrl = 'http://20.124.3.145:9090/criarConta';
      //this.deleteUrl =  'http://20.124.3.145:9090/deletarConta/';
      //this.recebeDadosAlterarContaUrl = 'http://20.124.3.145:9090/recebeDadosAlterarConta';
      //this.alterarUrl = 'http://20.124.3.145:9090/alterarConta';
  }

  public findAll(contas: ContasBancarias): Observable<ContasBancarias[]> {
    return this.http.post<ContasBancarias[]>(this.findUrl, contas);
  }

  public gerarRelatorio(contas: ContasBancarias): Observable<any> {
    return this.http.post<any>(this.gerarRelatorioConta, contas);
  }

  public save(contas: ContasBancarias) : Observable<ContasBancarias> {
    return this.http.post<ContasBancarias>(this.saveContasUrl, contas);
  }

  public delete(id: string) : Observable<any>{
    let headers = new HttpHeaders();
    headers.append("contas",btoa("id:"+id));
    return this.http.delete<any>(this.deleteUrl+id, { headers: headers });
  }

  public recebeDadosAlterarConta(conta: ContasBancarias) : Observable<ContasBancarias> {
    return this.http.post<ContasBancarias>(this.recebeDadosAlterarContaUrl, conta);
  }

  public alterarConta(conta: ContasBancarias) : Observable<ContasBancarias>{
    return this.http.put<ContasBancarias>(this.alterarUrl, conta);
  }

}
