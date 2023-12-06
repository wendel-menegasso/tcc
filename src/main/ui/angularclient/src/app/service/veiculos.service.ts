import { Injectable } from '@angular/core';
import { Veiculos } from '../model/veiculos';
import { Observable } from 'rxjs-compat';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VeiculosService {
  alterarUrl: string;
  saveVeiculoUrl: string;
  gerarRelatorioVeiculo: string;
  deleteUrl: string;
  findUrl: string;
  recebeDadosAlterarVeiculosUrl: string;
  constructor(private http: HttpClient) {
    this.alterarUrl = 'http://localhost:9090/alterarVeiculo';
    this.saveVeiculoUrl = 'http://localhost:9090/criarVeiculo';
    this.gerarRelatorioVeiculo = 'http://localhost:9090/gerarRelatorioVeiculo';
    this.deleteUrl = 'http://localhost:9090/deletarVeiculo/';
    this.findUrl = 'http://localhost:9090/listarVeiculo';
    this.recebeDadosAlterarVeiculosUrl = 'http://localhost:9090/recebeDadosAlterarVeiculo';
   }

  public alterarVeiculos(veiculos: Veiculos) : Observable<Veiculos>{
    return this.http.put<Veiculos>(this.alterarUrl, veiculos);
  }

  public save(veiculos: Veiculos) : Observable<Veiculos> {
    return this.http.post<Veiculos>(this.saveVeiculoUrl, veiculos);
  }

  public gerarRelatorio(veiculos: Veiculos): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post(this.gerarRelatorioVeiculo, veiculos, { responseType: 'blob', headers });
  }

  
  public delete(id: string) : Observable<any>{
    let headers = new HttpHeaders();
    headers.append("veiculos",btoa("id:"+id));
    return this.http.delete<any>(this.deleteUrl+id, { headers: headers });
  }

  public findAll(req: string): Observable<Veiculos[]> {
    return this.http.post<Veiculos[]>(this.findUrl, req);
  }

  public recebeDadosAlterarVeiculo(rendas: Veiculos) : Observable<Veiculos> {
    return this.http.post<Veiculos>(this.recebeDadosAlterarVeiculosUrl, rendas);
  }


}
