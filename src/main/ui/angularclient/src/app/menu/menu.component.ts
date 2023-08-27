import { Component, Input, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

public token: string;
public str: string;
public id: string


constructor( private route: ActivatedRoute ) {
}

  ngOnInit() {
    this.id = this.idUsuario;
  }
  @Input() idUsuario : string;
}
