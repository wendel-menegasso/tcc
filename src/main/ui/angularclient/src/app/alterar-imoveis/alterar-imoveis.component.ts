import { Component, Input, OnInit } from '@angular/core';
import { Imovel } from '../model/imovel';
import { ActivatedRoute, Router } from '@angular/router';
import { ImoveisService } from '../service/imoveis.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-alterar-imoveis',
  templateUrl: './alterar-imoveis.component.html',
  styleUrls: ['./alterar-imoveis.component.css']
})
export class AlterarImoveisComponent implements OnInit {

  imovel: Imovel;
  imovelArg: Imovel;
  retornoImovel: Imovel;
  imoveis: any[] = [];
  imovelCount = 0;
  token: string;

  valor: string;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private imovelService: ImoveisService,
    private location: Location) {
        this.imovel = new Imovel();
        this.imovelArg = new Imovel();
  }

  ngOnInit(): void {
  }

  onSubmit(){
    this.imovel.usuario = this.idUsuario;
      this.imovelService.alterarImoveis(this.imovel).subscribe(data =>{
        this.retornoImovel = data;
        if (this.retornoImovel != null){
          this.closePopup();
          alert('Alterado com sucesso!');
          this.token = '6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666';
          this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.idUsuario  } });
        }
      })
  }  

  displayStyle = "none";

  openPopup() {
    this.displayStyle = "block";
    this.imovelArg.id = this.idImoveis;
    this.imovelArg.usuario = this.idUsuario;
    this.imovelService.recebeDadosAlterarImoveis(this.imovelArg).subscribe(data => {
        this.imovel = data;      
    });
  }
  closePopup() {
      this.displayStyle = "none";
  }

  @Input() idImoveis : string;
  @Input() idUsuario : string;

}
