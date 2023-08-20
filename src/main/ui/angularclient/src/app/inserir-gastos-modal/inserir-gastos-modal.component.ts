import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Gastos } from '../model/gastos';
import { GastosService } from '../service/gastos.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-inserir-gastos-modal',
  templateUrl: './inserir-gastos-modal.component.html',
  styleUrls: ['./inserir-gastos-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class InserirGastosModalComponent implements OnInit {

  gastos: Gastos;
  gastosArray: any[] = [];
  gastosCount = 0;

  constructor(private route: ActivatedRoute,
    private router: Router,
      private gastosService: GastosService,
      private location: Location) {
    this.gastos = new Gastos();
}

onSubmit() {
  if (this.gastos.tipo == "Mercado"){
    this.gastos.tipo = "1";
  }
  if (this.gastos.tipo == "Alimentação"){
    this.gastos.tipo = "2";
  }
  if (this.gastos.tipo == "Farmácia"){
    this.gastos.tipo = "3";
  }
  if (this.gastos.tipo == "Médico"){
    this.gastos.tipo = "4";
  }
  if (this.gastos.tipo == "Mensalidade escolar"){
    this.gastos.tipo = "5";
  }
  if (this.gastos.tipo == "Livros escolares"){
    this.gastos.tipo = "6";
  }
  if (this.gastos.tipo == "Academia"){
    this.gastos.tipo = "7";
  }
  if (this.gastos.tipo == "Beleza"){
    this.gastos.tipo = "8";
  }
  if (this.gastos.tipo == "Automóvel"){
    this.gastos.tipo = "9";
  }
  if (this.gastos.tipo == "Domicílio"){
    this.gastos.tipo = "10";
  }
  if (this.gastos.tipo == "Empregados domésticos"){
    this.gastos.tipo = "11";
  }
  if (this.gastos.tipo == "Dívidas"){
    this.gastos.tipo = "12";
  }
  if (this.gastos.tipo == "Empréstimos"){
    this.gastos.tipo = "13";
  }
  if (this.gastos.tipo == "Entretenimento"){
    this.gastos.tipo = "14";
  }
  if (this.gastos.tipo == "Empresa"){
    this.gastos.tipo = "15";
  }
  if (this.gastos.tipo == "Investimentos"){
    this.gastos.tipo = "16";
  }
  if (this.gastos.tipo == "Outros"){
    this.gastos.tipo = "17";
  }

  this.gastosService.save(this.gastos).subscribe(data => {
    this.gastos = data;
    if (this.gastos != null){
        this.gotoGastosList();
    }
    });
}


gotoGastosList() {
  this.closePopup();
  alert('Salvo com sucesso!');
  location.reload();
}
  displayStyle = "none";

openPopup() {
  this.displayStyle = "block";
}
closePopup() {
  this.displayStyle = "none";
}

ngOnInit(): void {
}

}
