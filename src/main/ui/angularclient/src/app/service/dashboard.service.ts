// download.service.ts
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  constructor(private http: HttpClient) {}

  downloadFile(usuario: string): Observable<Blob> {
    const url = 'http://localhost:9090/graficos'; // Substitua pela URL do seu serviço de download

    // Configurações do cabeçalho para indicar que esperamos um arquivo binário
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.post(url, usuario, { responseType: 'blob', headers });
  }
}

