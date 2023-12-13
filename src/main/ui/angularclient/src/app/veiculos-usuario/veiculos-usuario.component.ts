import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VeiculosService } from '../service/veiculos.service';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';
import { Veiculos } from '../model/veiculos';
import { Subject } from 'rxjs';


@Component({
  selector: 'app-veiculos-usuario',
  templateUrl: './veiculos-usuario.component.html',
  styleUrls: ['./veiculos-usuario.component.css']
})
export class VeiculosUsuarioComponent implements OnInit {
  veiculoExportar: Veiculos;
  veiculosDelete: Veiculos;
  query: string;
  chaveValor: string[];
  chave: string;
  valor: string;
  idUser: string;
  veiculos: Veiculos[];
  token: string;
  destroy$: Subject<boolean> = new Subject<boolean>();
  page = 1;
  pag : Number = 1 ;
  pageSize = 4;

  constructor(    
    private router: Router,
    private veiculosService: VeiculosService,
    config: NgbPaginationConfig) {
        this.veiculosDelete = new Veiculos();
        config.size = 'sm';
        config.boundaryLinks = true;
        this.veiculoExportar = new Veiculos();
      }

      ngOnInit(): void {
        this.query = location.search.slice(1);
        this.chaveValor = this.query.split('=');
        this.chave = this.chaveValor[0];
        this.valor = this.chaveValor[1];
        this.idUser = this.valor;
        this.getTodosVeiculos();
      }

      getTodosVeiculos(): void {
        this.veiculosService.findAll(this.idUser).subscribe(data => {
          this.veiculos = data;
        });
      }

      exportar(){
        this.veiculoExportar.usuario = this.idUser;
        this.veiculosService.gerarRelatorio(this.veiculoExportar).subscribe((response: Blob) => {
              // Cria um objeto de URL temporário para o blob
            const url = window.URL.createObjectURL(response);
    
             const link = document.createElement('a');
             link.href = url;
             // Define o nome do arquivo para o link
             link.download = 'veiculos.csv';
          
             // Adiciona o link ao DOM e aciona o clique para iniciar o download
             document.body.appendChild(link);
             link.click();
          
             // Remove o link do DOM
             document.body.removeChild(link);  
    
            alert("Exportado com sucesso!");
        });
      }

      excluir(id: string){
        this.veiculosService.delete(id).subscribe(data => {
            this.veiculos[0] = data;
            if (this.veiculos[0] != null){
                this.link();
            }
            else{
                alert("Não foi possível excluir");
            }
          });
    }

    link(){
      alert("Excluido com sucesso");
      this.token = '5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555';
      this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.idUsuario  } });

    }

    ngOnDestroy() {
      this.destroy$.next(true);
      this.destroy$.unsubscribe();
    }

    @Input() idUsuario : string;

}
