import { Component, Input, OnInit } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ContasBancariasService } from '../service/contas-bancarias.service';
import { ContasBancarias } from '../model/contas-bancarias';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-alterar-contas-modal',
  templateUrl: './alterar-contas-modal.component.html',
  styleUrls: ['./alterar-contas-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class AlterarContasModalComponent implements OnInit {

  conta: ContasBancarias;
  contasArg: ContasBancarias;
  retornoConta: ContasBancarias;
  contasBancarias: any[] = [];
  contasCount = 0;
  token: string;
  saldo: string;

  tipos = [
    { id: 1, name: "Conta Poupança" },
    { id: 2, name: "Conta Salário" },
    { id: 3, name: "Conta Corrente" },
    { id: 4, name: "Conta Empresa" }
  ];

  modelo_tipo = {
    tipo_id: 3
  };

  bancos = [
    { id: 1, name: "Banco do Brasil" },
    { id: 2, name: "Bradesco" },
    { id: 3, name: "C6" },
    { id: 4, name: "Caixa Econômica Federal" },
    { id: 5, name: "Itaú" },
    { id: 6, name: "Nubank" },
    { id: 7, name: "Pan" },
    { id: 8, name: "Santander" },
    { id: 9, name: "Outros" }
  ];

  modelo_banco = {
    banco_id: 3
  };

  constructor(private route: ActivatedRoute,
          private router: Router,
            private contasBancariasService: ContasBancariasService) {
          this.conta = new ContasBancarias();
          this.contasArg = new ContasBancarias();
  }

  ngOnInit(): void {
  }

  onSubmit(){
    var regexp = new RegExp(/[A-Z a-z]/);
    var regexp2 = new RegExp(/^[1-9]([0-9]+)*\.\d{2}/);
    var testAgencia = regexp.test(this.conta.agencia);
    var testConta = regexp.test(this.conta.conta);
    var testValor = regexp2.test(this.conta.saldo);
    if (testAgencia == false){
      if (testConta == false){
        if (testValor == true){
          this.contasBancariasService.alterarConta(this.conta).subscribe(data =>{
          this.retornoConta = data;
          if (this.retornoConta != null){
            alert('Alterado com sucesso');
            this.token = '1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111';
            this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.idUsuario  } });
            this.closePopup();
    
          }})
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
    this.contasArg.id = this.idConta;
    this.contasArg.usuario = this.idUsuario;
    this.contasBancariasService.recebeDadosAlterarConta(this.contasArg).subscribe(data => {
      
      this.conta = data;
      
      const tipo = Number(this.conta.tipo);
      this.modelo_tipo.tipo_id = tipo;

      const banco = Number(this.conta.banco);
      this.modelo_banco.banco_id = banco;
      
      document.getElementById('banco').nodeValue = this.conta.banco;
      });
  }
  closePopup() {
    this.displayStyle = "none";
  }

  @Input() idConta : string;
  @Input() idUsuario : string;
}
