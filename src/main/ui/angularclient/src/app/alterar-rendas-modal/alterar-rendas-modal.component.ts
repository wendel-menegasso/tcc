import { Component, Input, OnInit } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RendasService } from '../service/rendas-service';
import { Rendas } from '../model/rendas';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { ContasBancariasService } from '../service/contas-bancarias.service';
import { ContasBancarias } from '../model/contas-bancarias';

@Component({
  selector: 'app-alterar-rendas-modal',
  templateUrl: './alterar-rendas-modal.component.html',
  styleUrls: ['./alterar-rendas-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class AlterarRendasModalComponent implements OnInit {

  renda: Rendas;
  rendasArg: Rendas;
  retornoRenda: Rendas;
  rendas: any[] = [];
  rendasCount = 0;
  token: string;
  valorAtual: number;
  valorAlterado: number;
  diferencaDeValores: number;
  contas: ContasBancarias;
  contaRetorno: ContasBancarias;

  tipos = [
    { id: 1, name: "Salario" },
    { id: 2, name: "Empresa" },
    { id: 3, name: "Aluguel" },
    { id: 4, name: "Ações" },
    { id: 5, name: "Juros Poupança" },
    { id: 6, name: "Tesouro Direto" },
    { id: 7, name: "Outros" }
  ];

  modelo_tipo = {
    tipo_id: 3
  };
  valor: string;

  constructor(private route: ActivatedRoute,
          private router: Router,
          private rendasService: RendasService,
          private contasService: ContasBancariasService,
          private location: Location) {
          this.renda = new Rendas();
          this.rendasArg = new Rendas();
  }

  ngOnInit(): void {
  }

  onSubmit(){
    this.renda.usuario = this.idUsuario;
    var regexp = new RegExp(/^[1-9]([0-9]+)*\.\d{2}/);
    var gasto = regexp.test(this.renda.valor);
    if (gasto == true){
      this.rendasService.alterarRendas(this.renda).subscribe(data =>{
        this.retornoRenda = data;
        if (this.retornoRenda != null){
              this.alterar();
        }
    });
    }
    else{
      alert("O campo valor deve ser preenchido no formato 1000.00");     
    }
  }

  alterar(){
    this.closePopup();
    alert('Alterado com sucesso!');
    this.token = '2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222';
    this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.idUsuario  } });
  }

  displayStyle = "none";

  openPopup() {
    this.displayStyle = "block";
    this.rendasArg.id = this.idRenda;
    this.rendasArg.usuario = this.idUsuario;
    this.rendasService.recebeDadosAlterarRendas(this.rendasArg).subscribe(data => {
      
      this.renda = data;
      
      const tipo = Number(this.renda.tipo);
      this.modelo_tipo.tipo_id = tipo;
      this.valorAtual = Number(this.renda.valor);
      document.getElementById('tipo').nodeValue = this.renda.tipo;
      });
  }
  closePopup() {
    this.displayStyle = "none";
  }

  @Input() idRenda : string;
  @Input() idUsuario : string;

}
