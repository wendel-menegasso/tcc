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
    this.contasBancariasService.alterarConta(this.conta).subscribe(data =>{
      this.retornoConta = data;
      if (this.retornoConta != null){
        alert('Alterado com sucesso');
        this.router.navigate(['/contas']);
      }
    })
  }

  displayStyle = "none";

  openPopup() {
    this.displayStyle = "block";
    this.contasArg.id = this.idConta;
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

  gotoAlterarConta() {
    this.router.navigate(['/home']);
  }

  @Input() idConta : string;

}
