import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Rendas } from '../model/rendas';
import { RendasService } from '../service/rendas-service';

@Component({
  selector: 'app-inserir-rendas-modal',
  templateUrl: './inserir-rendas-modal.component.html',
  styleUrls: ['./inserir-rendas-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class InserirRendasModalComponent implements OnInit {

  rendas: Rendas;
  rendasArray: any[] = [];
  rendasasCount = 0;

  constructor(private route: ActivatedRoute,
    private router: Router,
      private rendasService: RendasService) {
    this.rendas = new Rendas();
}

onSubmit() {
  if (this.rendas.tipo == "Salário"){
    this.rendas.tipo = "1";
  }
  if (this.rendas.tipo == "Empresa"){
    this.rendas.tipo = "2";
  }
  if (this.rendas.tipo == "Aluguel"){
    this.rendas.tipo = "3";
  }
  if (this.rendas.tipo == "Ações"){
    this.rendas.tipo = "4";
  }
  if (this.rendas.tipo == "Juros poupança"){
    this.rendas.tipo = "5";
  }
  if (this.rendas.tipo == "Tesouro direto"){
    this.rendas.tipo = "6";
  }
  if (this.rendas.tipo == "Outros"){
    this.rendas.tipo = "7";
  }

  this.rendasService.save(this.rendas).subscribe(data => {
    this.rendas = data;
    if (this.rendas != null){
        this.gotoRendasList();
    }
    });
}

  gotoRendasList() {
    alert('Salvo com sucesso!');
    this.router.navigate(['/ganhos']);
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
