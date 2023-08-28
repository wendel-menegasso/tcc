import { Component, Input, OnInit } from '@angular/core';
import { NgbAccordionConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
	selector: 'ngbd-accordion-config',
	templateUrl: './accordion-config.component.html',
	styleUrls: ['./accordion-config.component.css'],
	providers: [NgbAccordionConfig], // add the NgbAccordionConfig to the component providers
})
export class NgbdAccordionConfig {

	public token: string;
	public str: string;
	public id: string
	idURL: string;
	query: string;
	chaveValor: string[];
	chave: string;
	valor: string;

	constructor(config: NgbAccordionConfig) {
		// customize default values of accordions used by this component tree
		config.closeOthers = true;
		config.type = 'info';
	}
	Contas() {
	  this.getURL();
	  this.id = this.idUsuarioMenu;	
	  if (this.id === undefined){
		    window.location.href = "contasBancarias?id="+this.idURL;
	  }
	  else{
			window.location.href = "contasBancarias?id="+this.id;
	  }
    }
	Ganhos() {
		this.getURL();
		this.id = this.idUsuarioMenu;
		if (this.id === undefined){
			window.location.href = "ganhos?id="+this.idURL;
		}
		else{
			window.location.href = "ganhos?id="+this.id;	
		}	
	}
	Gastos() {
		this.getURL();
		this.id = this.idUsuarioMenu;
		if (this.id === undefined){
			window.location.href = "gastos?id="+this.idURL;
		}
		else{
			window.location.href = "gastos?id="+this.id;
		}
	}
	Logout() {
		window.location.href = "";
	}
	@Input() idUsuarioMenu : string;
	getURL(){
		this.query = location.search.slice(1);
		this.chaveValor = this.query.split('=');
		this.chave = this.chaveValor[0];
		this.valor = this.chaveValor[1];
		this.idURL = this.valor;
	}
}
