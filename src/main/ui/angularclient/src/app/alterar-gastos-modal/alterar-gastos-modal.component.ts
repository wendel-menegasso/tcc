import { Component, Input, OnInit } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { GastosService } from '../service/gastos.service';
import { Gastos } from '../model/gastos';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-alterar-gastos-modal',
  templateUrl: './alterar-gastos-modal.component.html',
  styleUrls: ['./alterar-gastos-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})

export class AlterarGastosModalComponent implements OnInit {

  gasto: Gastos;
  gastosArg: Gastos;
  retornoGasto: Gastos;
  gastos: any[] = [];
  gastosCount = 0;

  tipos = [
    { id: 1, name: "Mercado" },
    { id: 2, name: "Alimentação" },
    { id: 3, name: "Farmácia" },
    { id: 4, name: "Médico" },
    { id: 5, name: "Mensalidade escolar" },
    { id: 6, name: "Livros escolares" },
    { id: 7, name: "Academia" },
    { id: 8, name: "Beleza" },
    { id: 9, name: "Automóvel" },
    { id: 10, name: "Domicílio" },
    { id: 11, name: "Empregados domésticos" },
    { id: 12, name: "Dívidas" },
    { id: 13, name: "Empréstimos" },
    { id: 14, name: "Entretenimento" },
    { id: 15, name: "Empresa" },
    { id: 16, name: "Investimentos" },
    { id: 17, name: "Outros" }
  ];

  modelo_tipo = {
    tipo_id: 3
  };

  constructor(private route: ActivatedRoute,
    private router: Router,
      private gastosService: GastosService,
      private location: Location) {
    this.gasto = new Gastos();
    this.gastosArg = new Gastos();
}

  ngOnInit(): void {
  }

  onSubmit(){
    this.gastosService.alterarRendas(this.gasto).subscribe(data =>{
      this.retornoGasto = data;
      if (this.retornoGasto != null){
        this.closePopup();
        alert('Alterado com sucesso!');
        location.reload();
      }
    })
  }

  displayStyle = "none";

  openPopup() {
    this.displayStyle = "block";
    this.gastosArg.id = this.idGastos;
    this.gastosService.recebeDadosAlterarRendas(this.gastosArg).subscribe(data => {
      
      this.gasto = data;
      
      const tipo = Number(this.gasto.tipo);
      this.modelo_tipo.tipo_id = tipo;

      document.getElementById('tipo').nodeValue = this.gasto.tipo;
      });
  }
  closePopup() {
    this.displayStyle = "none";
  }

  @Input() idGastos : string;

}
