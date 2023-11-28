import { Component, Input, OnInit } from '@angular/core';
import { DashboardService } from '../service/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  id:string;  
  gastosCount = 0;
  idUser: string;
  query: string;
  chaveValor: string[];
  chave: string;
  valor: string;
  page = 1;
  pag : Number = 1 ;
  pageSize = 4;
  contador : Number = 4;
  token: string;
  imgSrc: Promise<string>;

  constructor(private downloadService: DashboardService) {}

  ngOnInit(): void {
    this.query = location.search.slice(1);
    this.chaveValor = this.query.split('=');
    this.chave = this.chaveValor[0];
    this.valor = this.chaveValor[1];
    this.idUser = this.valor;
    this.download();
  }
  
    download(): void {
      this.downloadService.downloadFile(this.idUser).subscribe(
        (response: Blob) => {

          // Cria um objeto de URL temporário para o blob
          const url = window.URL.createObjectURL(response);

          const img = document.createElement('img');
          img.src = url;
  
          const dashboard = document.getElementById('dashboard');

          dashboard.appendChild(img);
  
        },
        (error) => {
          console.error('Erro ao baixar o arquivo', error);
        }
      );
    }

    @Input() idUsuario : string;

}
