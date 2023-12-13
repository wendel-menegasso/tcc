import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-tela-inicial',
  templateUrl: './tela-inicial.component.html',
  styleUrls: ['./tela-inicial.component.css']
})
export class TelaInicialComponent implements OnInit {

public token: string;
public str: string;
public id: string


constructor( private route: ActivatedRoute,
  private router: Router ) {
}

  ngOnInit() {
    this.route.queryParamMap
    .subscribe((params) => {
      var obj = params;
      this.str = JSON.stringify(obj);
      const string = this.str.replace( "params", "");
      this.token = string.substring(14, 114);
      this.id = string.substring(122, string.length - 3);
      if (this.token === '1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111'){
          this.router.navigate(['/contasBancarias'], { queryParams: { 'id': this.id  } });
      }
      if (this.token === '2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222'){
          this.router.navigate(['/ganhos'], { queryParams: { 'id': this.id  } });
      }
      if (this.token === '3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333'){
          this.router.navigate(['/gastos'], { queryParams: { 'id': this.id  } });
      }
      if (this.token === '5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555'){
        this.router.navigate(['/veiculos'], { queryParams: { 'id': this.id  } });
      }
      if (this.token === '6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666'){
        this.router.navigate(['/imoveis'], { queryParams: { 'id': this.id  } });
      }
    }
  );
  

  }
}
