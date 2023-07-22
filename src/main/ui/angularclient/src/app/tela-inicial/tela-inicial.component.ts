import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-tela-inicial',
  templateUrl: './tela-inicial.component.html',
  styleUrls: ['./tela-inicial.component.css']
})
export class TelaInicialComponent implements OnInit {

public token: string;
public str: string;
public id: string


constructor( private route: ActivatedRoute ) {
}

  ngOnInit() {
this.route.queryParamMap
  .subscribe((params) => {
    var obj = params;
    this.str = JSON.stringify(obj);
    const string = this.str.replace( "params", "");
    this.token = string.substring(14, 114);
    this.id = string.substring(122, string.length - 3);
    console.log(this.token);
    console.log(this.id);
  }
);

  }

}
