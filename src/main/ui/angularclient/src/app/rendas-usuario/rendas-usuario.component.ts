import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-rendas-usuario',
  templateUrl: './rendas-usuario.component.html',
  styleUrls: ['./rendas-usuario.component.css']
})
export class RendasUsuarioComponent implements OnInit {

  id: string;
  query: string;
  chaveValor: string[];
  chave: string;
  valor: string;

  constructor() { }

  ngOnInit(): void {
    this.query = location.search.slice(1);
    this.chaveValor = this.query.split('=');
    this.chave = this.chaveValor[0];
    this.valor = this.chaveValor[1];
    this.id = this.valor;
  }

}
