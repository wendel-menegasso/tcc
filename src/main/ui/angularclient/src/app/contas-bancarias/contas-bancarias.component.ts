import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContasBancariasService } from '../service/contas-bancarias.service';
import { ContasBancarias } from '../model/contas-bancarias';
import { TipoConta } from '../model/tipo-conta';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-contas-bancarias',
  templateUrl: './contas-bancarias.component.html',
  styleUrls: ['./contas-bancarias.component.css']
})
export class ContasBancariasComponent implements OnInit {

  contas: ContasBancarias;
  contasBancarias: ContasBancarias[] = [];
  accounts: ContasBancarias[] = [];
  contasCount = 0;
    destroy$: Subject<boolean> = new Subject<boolean>();

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private contasBancariasService: ContasBancariasService) {
    this.contas = new ContasBancarias();
  }

  ngOnInit(): void {
    this.getTodasContas();
  }

    getTodasContas(): void {
      this.contasBancariasService.findAll().pipe(takeUntil(this.destroy$)).subscribe((contasBancarias: ContasBancarias[]) => {
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
              account.id = this.contasBancarias[i].id;
              account.agencia = this.contasBancarias[i].agencia;
              account.banco = this.contasBancarias[i].banco;
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

          console.log(this.contasBancarias);
      });
    }
    ngOnDestroy() {
      this.destroy$.next(true);
      this.destroy$.unsubscribe();
    }

}
