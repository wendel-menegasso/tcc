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

	constructor(config: NgbAccordionConfig) {
		// customize default values of accordions used by this component tree
		config.closeOthers = true;
		config.type = 'info';
	}
	Contas() {
	  this.id = this.idUsuarioMenu;
      window.location.href = "contasBancarias?id="+this.id;
    }
	Ganhos() {
		this.id = this.idUsuarioMenu;
	    window.location.href = "ganhos?id="+this.id;	
	}
	Gastos() {
		this.id = this.idUsuarioMenu;
	    window.location.href = "gastos?id="+this.id;
	}
	@Input() idUsuarioMenu : string;
}
