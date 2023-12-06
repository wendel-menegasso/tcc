import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VeiculosService } from '../service/veiculos.service';
import { Veiculos } from '../model/veiculos';

@Component({
  selector: 'app-inserir-veiculos',
  templateUrl: './inserir-veiculos.component.html',
  styleUrls: ['./inserir-veiculos.component.css']
})
export class InserirVeiculosComponent implements OnInit {

  veiculo: Veiculos;
  token: string;
  constructor(private route: ActivatedRoute,
    private router: Router,
      private veiculosService: VeiculosService)
 {
    this.veiculo = new Veiculos();
}

  ngOnInit(): void {
  }

  onSubmit() {
    this.veiculo.usuario = this.idUsuario;
    this.veiculosService.save(this.veiculo).subscribe(data => {
    this.veiculo = data;
    if (this.veiculo != null){
      this.gotoVeiculosList();
    }
    });
  }

  gotoVeiculosList() {
    this.closePopup();
    alert('Salvo com sucesso!');
    this.token = '5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555';
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
