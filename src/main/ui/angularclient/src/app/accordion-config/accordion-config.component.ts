import { Component, Input} from '@angular/core';
import { NgbAccordionConfig } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';


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
	url: string[];

	constructor(config: NgbAccordionConfig,
		private route: ActivatedRoute,
		private router: Router) {
		// customize default values of accordions used by this component tree
		config.closeOthers = true;
		config.type = 'info';
	}

	Contas() {
		this.getURL();
		this.id = this.idUsuarioMenu;
		if (this.id === undefined){
			this.router.navigate(['/contasBancarias'], { queryParams: { 'id': this.idURL  } });
		}
		else{
			this.router.navigate(['/contasBancarias'], { queryParams: { 'id': this.id  } });
		}
  	}
	Ganhos() {
		this.getURL();
		this.id = this.idUsuarioMenu;
		if (this.id === undefined){
			this.router.navigate(['/ganhos'], { queryParams: { 'id': this.idURL  } });
		}
		else{
			this.router.navigate(['/ganhos'], { queryParams: { 'id': this.id  } });
		}
	}
	Gastos() {
		this.getURL();
		this.id = this.idUsuarioMenu;
		if (this.id === undefined){
			this.router.navigate(['/gastos'], { queryParams: { 'id': this.idURL  } });
		}
		else{
		  	this.router.navigate(['/gastos'], { queryParams: { 'id': this.id  } });
		}
	}

	Dashboard() {
		this.getURL();
		this.id = this.idUsuarioMenu;
		if (this.id === undefined){
			this.router.navigate(['/dashboard'], { queryParams: { 'id': this.idURL  } });
		}
		else{
			this.router.navigate(['/dashboard'], { queryParams: { 'id': this.id  } });
		}
	}

	Imovel(){
		this.getURL();
		this.id = this.idUsuarioMenu;
		if (this.id === undefined){
			this.router.navigate(['/imoveis'], { queryParams: { 'id': this.idURL  } });
		}
		else{
			this.router.navigate(['/imoveis'], { queryParams: { 'id': this.id  } });
		}
	}

	Veiculos() {
		this.getURL();
		this.id = this.idUsuarioMenu;
		if (this.id === undefined){
			this.router.navigate(['/veiculos'], { queryParams: { 'id': this.idURL  } });
			}
		else{
			this.router.navigate(['/veiculos'], { queryParams: { 'id': this.id  } });
		}
	}

	Home() {
		this.getURL();
		this.token = '4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444';
		this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.chaveValor[1] } });;
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
