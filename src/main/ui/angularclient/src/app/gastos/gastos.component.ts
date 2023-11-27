import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GastosService } from '../service/gastos.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Gastos } from '../model/gastos';
import { Location } from '@angular/common';
import {NgbPaginationConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-gastos',
  templateUrl: './gastos.component.html',
  providers: [NgbPaginationConfig],
  styleUrls: ['./gastos.component.css']
})
export class GastosComponent implements OnInit {
  id:string;  
  destroy$: Subject<boolean> = new Subject<boolean>();
  gastos: Gastos[];
  gastosDelete: Gastos;
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
    valorGasto: string;
    gasto: Gastos;

  constructor(    
    private route: ActivatedRoute,
    private router: Router,
    private gastosService: GastosService,
    private location: Location,
    config: NgbPaginationConfig) {
        this.gastosDelete = new Gastos();
        config.size = 'sm';
        config.boundaryLinks = true;
        this.gasto = new Gastos();
      }
  ngOnInit(): void {
    this.query = location.search.slice(1);
    this.chaveValor = this.query.split('=');
    this.chave = this.chaveValor[0];
    this.valor = this.chaveValor[1];
    this.idUser = this.valor;
    this.getTodosGastos();
  }

  getTodosGastos(): void {
    this.gastosService.findAll(this.idUser).pipe(takeUntil(this.destroy$)).subscribe((gastos: Gastos[]) => {
        this.gastosCount = gastos.length;
        this.gastos = gastos;
        var gastoArray :Gastos[] = [];
        for (var i=0; i<this.gastosCount;i++){
            var gasto: Gastos = new Gastos();
            if (this.gastos[i].tipo == "1"){
                gasto.tipo = "Mercado";
            }
            if (this.gastos[i].tipo == "2"){
                gasto.tipo = "Alimentação";
            }
            if (this.gastos[i].tipo == "3"){
                gasto.tipo = "Farmácia";
            }
            if (this.gastos[i].tipo == "4"){
                gasto.tipo = "Médico";
            }
            if (this.gastos[i].tipo == "5"){
                gasto.tipo = "Mensalidade escolar";
            }
            if (this.gastos[i].tipo == "6"){
                gasto.tipo = "Livros escolares";
            }
            if (this.gastos[i].tipo == "7"){
                gasto.tipo = "Academia";
            }
            if (this.gastos[i].tipo == "8"){
                gasto.tipo = "Beleza";
            }
            if (this.gastos[i].tipo == "9"){
                gasto.tipo = "Automóvel";
            }
            if (this.gastos[i].tipo == "10"){
                gasto.tipo = "Domícilio";
            }
            if (this.gastos[i].tipo == "11"){
                gasto.tipo = "Empregados domésticos";
            }
            if (this.gastos[i].tipo == "12"){
                gasto.tipo = "Dívidas";
            }
            if (this.gastos[i].tipo == "13"){
                gasto.tipo = "Empréstimos";
            }
            if (this.gastos[i].tipo == "14"){
                gasto.tipo = "Entretenimento";
            }
            if (this.gastos[i].tipo == "15"){
                gasto.tipo = "Empresa";
            }
            if (this.gastos[i].tipo == "16"){
                gasto.tipo = "Investimentos";
            }
            if (this.gastos[i].tipo == "17"){
                gasto.tipo = "Outros";
            }

            gasto.id = this.gastos[i].id;
            gasto.nome = this.gastos[i].nome;
            gasto.valor = this.gastos[i].valor;
            gasto.usuario = this.gastos[i].usuario;
            gasto.data = this.gastos[i].data;
            gastoArray[i] = gasto;
        }

        for (var i=0; i<this.gastosCount;i++){
            this.gastos.pop();
        }
        for (var i=0; i<this.gastosCount;i++){
            this.gastos.push(gastoArray[i]);
        }
        this.gasto.usuario = this.idUser;
    });
  }

  exportar(){
    this.gastosService.gerarRelatorio(this.gasto).subscribe((response: Blob) =>  {
          // Cria um objeto de URL temporário para o blob
        const url = window.URL.createObjectURL(response);

        const link = document.createElement('a');
        link.href = url;
        // Define o nome do arquivo para o link
        link.download = 'gastos.csv';

        // Adiciona o link ao DOM e aciona o clique para iniciar o download
        document.body.appendChild(link);
        link.click();

        // Remove o link do DOM
        document.body.removeChild(link);      
        alert("Exportado com sucesso!");
        
  });
}

  excluir(id: string){
    this.gastosService.delete(id).subscribe(data => {
        this.gastos = data;
        if (this.gastos != null){
            alert("Excluido com sucesso");
            this.token = '3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333';
            this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.idUsuario  } });

        }
        else{
            alert("Não foi possível excluir");
        }
        });
}

  ngOnDestroy() {
    this.destroy$.next(true);
    this.destroy$.unsubscribe();
  }
  @Input() idUsuario : string;
}
