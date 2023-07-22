import { Component, OnInit } from '@angular/core';
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
