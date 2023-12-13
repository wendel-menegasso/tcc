import { Component, Input, OnInit } from '@angular/core';
import { Imovel } from '../model/imovel';
import { ActivatedRoute, Router } from '@angular/router';
import { ImoveisService } from '../service/imoveis.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-inserir-imoveis',
  templateUrl: './inserir-imoveis.component.html',
  styleUrls: ['./inserir-imoveis.component.css']
})
export class InserirImoveisComponent implements OnInit {

  imovel: Imovel;
  imoveisArray: Imovel[] = [];
  imoveisCount = 0;
  token: string;

  constructor(private route: ActivatedRoute,
    private router: Router,
    private imoveisService: ImoveisService,
    private location: Location) {
          this.imovel = new Imovel();
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.imovel.usuario = this.idUsuario;
      this.imoveisService.save(this.imovel).subscribe(data => {
        this.imovel = data;
        if (this.imovel != null){
            this.gotoImoveisList();
        }
        });
  }
  gotoImoveisList() {
    this.closePopup();
    alert('Salvo com sucesso!');
    this.token = '6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666';
    this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.idUsuario  } });
  
  }

  
    displayStyle = "none";
  
  openPopup() {
    this.displayStyle = "block";
  }
  closePopup() {
    this.displayStyle = "none";
  }

  @Input() idUsuario : string;
}
