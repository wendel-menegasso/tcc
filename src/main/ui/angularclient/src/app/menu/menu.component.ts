import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

public username: string;


constructor( private route: ActivatedRoute ) {
}

  ngOnInit() {
this.route.queryParamMap
  .subscribe((params) => {
    const orderObj = { ...params.keys, ...params };
    var txt = JSON.stringify(orderObj);
    this.username = txt;
    console.log(this.username);
  }
);

  }
}
