import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ContasBancariasService } from '../service/contas-bancarias.service';
import { ContasBancarias } from '../model/contas-bancarias';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-inserir-contas-modal',
  templateUrl: './inserir-contas-modal.component.html',
  styleUrls: ['./inserir-contas-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class InserirContasModalComponent implements OnInit{

  contas: ContasBancarias;
  contasBancarias: any[] = [];
  contasCount = 0;
  token: string;

  constructor(private route: ActivatedRoute,
          private router: Router,
            private contasBancariasService: ContasBancariasService) {
          this.contas = new ContasBancarias();
  }
    onSubmit() {
      if (this.contas.banco == "Banco do Brasil"){
        this.contas.banco = "1";
      }
      if (this.contas.banco == "Bradesco"){
        this.contas.banco = "2";
      }
      if (this.contas.banco == "C6"){
        this.contas.banco = "3";
      }
      if (this.contas.banco == "Caixa Econômica Federal"){
        this.contas.banco = "4";
      }
      if (this.contas.banco == "Itaú"){
        this.contas.banco = "5";
      }
      if (this.contas.banco == "Nubank"){
        this.contas.banco = "6";
      }
      if (this.contas.banco == "Pan"){
        this.contas.banco = "7";
      }
      if (this.contas.banco == "Santander"){
        this.contas.banco = "8";
      }
      if (this.contas.banco == "Outros"){
        this.contas.banco = "9";
      }

  		if (this.contas.tipo == "Conta Poupança"){
  		  this.contas.tipo = "1";
  		}
  		if (this.contas.tipo == "Conta Salário"){
  		  this.contas.tipo = "2";
  		}
  		if (this.contas.tipo == "Conta Corrente"){
  		  this.contas.tipo = "3";
  		}
  		if (this.contas.tipo == "Conta Empresa"){
  		  this.contas.tipo = "4";
  		}
      this.contas.usuario = this.idUsuario;

      var regexp = new RegExp(/[A-Z a-z]/);
      var regexp2 = new RegExp(/^[1-9]([0-9]+)*\.\d{2}/);
      var testAgencia = regexp.test(this.contas.agencia);
      var testConta = regexp.test(this.contas.conta);
      var testValor = regexp2.test(this.contas.saldo);
      if (testAgencia == false){
        if (testConta == false){
          if (testValor == true){
            this.contasBancariasService.save(this.contas).subscribe(data => {
              this.contas = data;
              if (this.contas != null){
                alert('Salvo com sucesso!');
                this.token = '1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111';
                this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.idUsuario  } });
                this.closePopup();
      
              }
            });
          }
          else{
            alert("O campo saldo deve ser preenchido no formato 1000.00");
          }
        }
        else{
          alert("O campo conta deve conter apenas números");
        }
      }
      else{
        alert("O campo agência deve conter apenas números");
      }
    }
    displayStyle = "none";

      openPopup() {
        this.displayStyle = "block";
      }
      closePopup() {
        this.displayStyle = "none";
      }
      ngOnInit() {}
      @Input() idUsuario : string;
}
