import { Component, Input, OnInit } from '@angular/core';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';
import { Subject } from 'rxjs';
import { Imovel } from '../model/imovel';
import { ActivatedRoute, Router } from '@angular/router';
import { ImoveisService } from '../service/imoveis.service';
import { takeUntil } from 'rxjs/operators';
import { Location } from '@angular/common';

@Component({
  selector: 'app-imoveis-usuario',
  templateUrl: './imoveis-usuario.component.html',
  providers: [NgbPaginationConfig],
  styleUrls: ['./imoveis-usuario.component.css']
})

export class ImoveisUsuarioComponent implements OnInit {

  id:string;  
  destroy$: Subject<boolean> = new Subject<boolean>();
  imoveis: Imovel[];
  imoveisDelete: Imovel;
  req: string;
  imoveisCount = 0;
  idUser: string;
  query: string;
  chaveValor: string[];
  chave: string;
  valor: string;
  page = 1;
  pag : Number = 1;
  pageSize = 4;
  contador : Number = 4;
  token: string;
  imovelRelatorio: Imovel;
  url: string;


  constructor(    
    private route: ActivatedRoute,
    private router: Router,
    private imoveisService: ImoveisService,
    private location: Location,
    config: NgbPaginationConfig) {
        this.imoveisDelete = new Imovel();
        config.size = 'sm';
        config.boundaryLinks = true;
        this.imovelRelatorio = new Imovel();
        this.imoveis = [];
      }

  ngOnInit(): void {
    this.query = location.search.slice(1);
    this.chaveValor = this.query.split('=');
    this.chave = this.chaveValor[0];
    this.valor = this.chaveValor[1];
    this.idUser = this.valor;
    this.getTodosImoveis();
  }
      getTodosImoveis(): void {
        this.imoveisService.findAll(this.idUser).subscribe(data => {
          this.imoveis = data;
        });
      }
    
      exportar(){
        this.imoveisService.gerarRelatorio(this.imovelRelatorio).subscribe((response: Blob) => {
              // Cria um objeto de URL temporário para o blob
            const url = window.URL.createObjectURL(response);
    
             const link = document.createElement('a');
             link.href = url;
             // Define o nome do arquivo para o link
             link.download = 'imoveis.csv';
          
             // Adiciona o link ao DOM e aciona o clique para iniciar o download
             document.body.appendChild(link);
             link.click();
          
             // Remove o link do DOM
             document.body.removeChild(link);  
    
            alert("Exportado com sucesso!");
        });
      }

      excluir(id: string){
        this.imoveisService.delete(id).subscribe(data => {
            this.imoveis[0] = data;
            if (this.imoveis[0] != null){
                this.link();
            }
            else{
                alert("Não foi possível excluir");
            }
          });
    }
    
    link(){
      alert("Excluido com sucesso");
      this.token = '6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666';
      this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.idUsuario  } });
    }
    
      ngOnDestroy() {
        this.destroy$.next(true);
        this.destroy$.unsubscribe();
      }
      @Input() idUsuario : string;

}
