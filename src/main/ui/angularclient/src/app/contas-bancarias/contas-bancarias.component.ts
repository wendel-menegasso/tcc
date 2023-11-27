import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContasBancariasService } from '../service/contas-bancarias.service';
import { ContasBancarias } from '../model/contas-bancarias';
import { TipoConta } from '../model/tipo-conta';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import {NgbPaginationConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-contas-bancarias',
  templateUrl: './contas-bancarias.component.html',
  styleUrls: ['./contas-bancarias.component.css']
})
export class ContasBancariasComponent implements OnInit {

	id:string;
	conta: ContasBancarias;
  contas: ContasBancarias;
  contasBancarias: ContasBancarias[] = [];
  accounts: ContasBancarias[] = [];
  contasCount = 0;
    destroy$: Subject<boolean> = new Subject<boolean>();
	query: string;
	chaveValor: string[];
	chave: string;
	valor: string;
	page = 1;
	pag : Number = 1 ;
	pageSize = 4;
	contador : Number = 4;
	token: string;
	saldo: string;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private contasBancariasService: ContasBancariasService,
		config: NgbPaginationConfig) {
    this.contas = new ContasBancarias();
	this.conta = new ContasBancarias();
	config.size = 'sm';
	config.boundaryLinks = true;
  }

  ngOnInit(): void {
	this.query = location.search.slice(1);
    this.chaveValor = this.query.split('=');
    this.chave = this.chaveValor[0];
    this.valor = this.chaveValor[1];
	this.conta.usuario = this.valor;
	this.getTodasContas();	
  }

    getTodasContas(): void {
      this.contasBancariasService.findAll(this.conta).pipe(takeUntil(this.destroy$)).subscribe((contasBancarias: ContasBancarias[]) => {
  		    this.contasCount = contasBancarias.length;
  		    this.contasBancarias = contasBancarias;
  		    var accounts :ContasBancarias[] = [];
  		    for (var i=0; i<this.contasCount;i++){
  		        var account: ContasBancarias = new ContasBancarias();
  		        if (this.contasBancarias[i].tipo == "1"){
  		            account.tipo = "Poupança";
  		        }
  		        if (this.contasBancarias[i].tipo == "2"){
  		            account.tipo = "Salário";
  		        }
  		        if (this.contasBancarias[i].tipo == "3"){
  		            account.tipo = "Corrente";
  		        }
  		        if (this.contasBancarias[i].tipo == "4"){
  		            account.tipo = "Empresa";
  		        }
  		        if (this.contasBancarias[i].banco == "1"){
  		            account.banco = "Banco do Brasil";
  		        }
  		        if (this.contasBancarias[i].banco == "2"){
  		            account.banco = "Bradesco";
  		        }
  		        if (this.contasBancarias[i].banco == "3"){
  		            account.banco = "C6";
  		        }
  		        if (this.contasBancarias[i].banco == "4"){
  		            account.banco = "Caixa Econômica Federal";
  		        }
  		        if (this.contasBancarias[i].banco == "5"){
  		            account.banco = "Itaú";
  		        }
  		        if (this.contasBancarias[i].banco == "6"){
  		            account.banco = "Nubank";
  		        }
  		        if (this.contasBancarias[i].banco == "7"){
  		            account.banco = "Pan";
  		        }
  		        if (this.contasBancarias[i].banco == "8"){
  		            account.banco = "Santander";
  		        }
				  if (this.contasBancarias[i].banco == "9"){
					account.banco = "Outros";
				}
			  
              account.id = this.contasBancarias[i].id;
              account.agencia = this.contasBancarias[i].agencia;
			  account.saldo = this.contasBancarias[i].saldo;
              account.conta = this.contasBancarias[i].conta;
              account.usuario = this.contasBancarias[i].usuario;
              accounts[i] = account;
  		    }

  		    for (var i=0; i<this.contasCount;i++){
  		        this.contasBancarias.pop();
  		    }
  		    for (var i=0; i<this.contasCount;i++){
  		        this.contasBancarias.push(accounts[i]);
  		    }
      });
    }

	exportar(){
		this.contasBancariasService.gerarRelatorio(this.conta).subscribe((response: Blob) => {
          // Cria um objeto de URL temporário para o blob
		  const url = window.URL.createObjectURL(response);

		  const link = document.createElement('a');
		  link.href = url;
		  // Define o nome do arquivo para o link
		  link.download = 'contas.csv';
  
		  // Adiciona o link ao DOM e aciona o clique para iniciar o download
		  document.body.appendChild(link);
		  link.click();
  
		  // Remove o link do DOM
		  document.body.removeChild(link);      
		  alert("Exportado com sucesso!");			
	  });
	}
	excluir(id: string){
		this.contasBancariasService.delete(id).subscribe(data => {
			this.contas = data;
			if (this.contas != null){
				alert('Excluido com sucesso!');
				this.token = '1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111';
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
