import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModalConfig, NgbModal, NgbCalendar, NgbDate, NgbDateStruct, NgbInputDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { Gastos } from '../model/gastos';
import { GastosService } from '../service/gastos.service';
import { Location } from '@angular/common';
import { OrigensService } from '../service/origens.service';
import { Origem } from '../model/origem';

@Component({
  selector: 'app-inserir-gastos-modal',
  templateUrl: './inserir-gastos-modal.component.html',
  styleUrls: ['./inserir-gastos-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class InserirGastosModalComponent implements OnInit {

  model: NgbDateStruct;

  gastos: Gastos;
  gastosArray: any[] = [];
  gastosCount = 0;
  token: string;
  origens: Origem[];

  constructor(private route: ActivatedRoute,
    private router: Router,
      private gastosService: GastosService,
      private origensService: OrigensService,
      private location: Location,
      config: NgbInputDatepickerConfig, 
      calendar: NgbCalendar) {
    this.gastos = new Gastos();
    		// customize default values of datepickers used by this component tree
		config.minDate = { day: 1, month: 1, year: 1900 };
		config.maxDate = {day: 31, month: 12, year: 2099 };

		// days that don't belong to current month are not visible
		config.outsideDays = 'hidden';

		// weekends are disabled
		config.markDisabled = (date: NgbDate) => calendar.getWeekday(date) >= 6;

		// setting datepicker popup to close only on click outside
		config.autoClose = 'outside';

		// setting datepicker popup to open above the input
		config.placement = ['right', 'right'];
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
  this.gastos.usuario = this.idUsuario;
  this.gastos.data = this.model.day + '-' + this.model.month + '-' + this.model.year;
  var regexp = new RegExp(/^[1-9]([0-9]+)*\.\d{2}/);
  var valor = regexp.test(this.gastos.valor);
  if (valor == true){
    this.gastosService.save(this.gastos).subscribe(data => {
      this.gastos = data;
      if (this.gastos != null){
          this.gotoGastosList();
      }
      });
  }
  else{
    alert("O campo valor deve ser preenchido no formato 1000.00");   
  }
}


gotoGastosList() {
  this.closePopup();
  alert('Salvo com sucesso!');
  this.token = '3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333';
  this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.idUsuario  } });

}

carregaOrigem(){
  this.origensService.findAll(this.idUsuario).subscribe(data => {
    this.origens = data;
    });
}

  displayStyle = "none";

openPopup() {
  this.displayStyle = "block";
}
closePopup() {
  this.displayStyle = "none";
}

ngOnInit(): void {
  this.carregaOrigem();
}
@Input() idUsuario : string;
}
