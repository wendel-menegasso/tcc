import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RendasService } from '../service/rendas-service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { Rendas } from '../model/rendas';
import { Location } from '@angular/common';
import {NgbPaginationConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-rendas',
  templateUrl: './rendas.component.html',
  providers: [NgbPaginationConfig],
  styleUrls: ['./rendas.component.css']
})
export class RendasComponent implements OnInit {
  id:string;  
  destroy$: Subject<boolean> = new Subject<boolean>();
  rendas: Rendas[];
  rendasDelete: Rendas;
  req: string;
  rendasCount = 0;
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
    valorRenda: string;

  constructor(    
    private route: ActivatedRoute,
    private router: Router,
    private rendasService: RendasService,
    private location: Location,
    config: NgbPaginationConfig) {
        this.rendasDelete = new Rendas();
        config.size = 'sm';
        config.boundaryLinks = true;
      }
  ngOnInit(): void {
    this.query = location.search.slice(1);
    this.chaveValor = this.query.split('=');
    this.chave = this.chaveValor[0];
    this.valor = this.chaveValor[1];
    this.idUser = this.valor;
    this.getTodasRendas();
  }

  getTodasRendas(): void {
    this.rendasService.findAll(this.idUser).pipe(takeUntil(this.destroy$)).subscribe((rendas: Rendas[]) => {
        this.rendasCount = rendas.length;
        this.rendas = rendas;
        var rendaArray :Rendas[] = [];
        for (var i=0; i<this.rendasCount;i++){
            var renda: Rendas = new Rendas();
            if (this.rendas[i].tipo == "1"){
                renda.tipo = "Salário";
            }
            if (this.rendas[i].tipo == "2"){
                renda.tipo = "Empresa";
            }
            if (this.rendas[i].tipo == "3"){
                renda.tipo = "Aluguel";
            }
            if (this.rendas[i].tipo == "4"){
                renda.tipo = "Ações";
            }
            if (this.rendas[i].tipo == "5"){
                renda.tipo = "Juros Poupança";
            }
            if (this.rendas[i].tipo == "6"){
                renda.tipo = "Tesouro Direto";
            }
            if (this.rendas[i].tipo == "7"){
                renda.tipo = "Outros";
            }
      
            renda.id = this.rendas[i].id;
            renda.nome = this.rendas[i].nome;
            renda.valor = this.rendas[i].valor;
            renda.usuario = this.rendas[i].usuario;
            renda.data = this.rendas[i].data;
            rendaArray[i] = renda;
        }

        for (var i=0; i<this.rendasCount;i++){
            this.rendas.pop();
        }
        for (var i=0; i<this.rendasCount;i++){
            this.rendas.push(rendaArray[i]);
        }
    });
  }

  excluir(id: string){
    this.rendasService.delete(id).subscribe(data => {
        this.rendas = data;
        if (this.rendas != null){
            alert("Excluido com sucesso");
            this.token = '2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222';
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
