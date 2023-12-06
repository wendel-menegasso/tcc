import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VeiculosService } from '../service/veiculos.service';
import { Veiculos } from '../model/veiculos';

@Component({
  selector: 'app-alterar-veiculos',
  templateUrl: './alterar-veiculos.component.html',
  styleUrls: ['./alterar-veiculos.component.css']
})
export class AlterarVeiculosComponent implements OnInit {

  veiculos: Veiculos;
  retornoVeiculos: Veiculos;
  token: string;
  veiculosArg: Veiculos;
  veiculo: Veiculos;

  constructor(private router: Router,
      private veiculosService: VeiculosService) {
          this.veiculos = new Veiculos();
          this.retornoVeiculos = new Veiculos();
          this.veiculosArg = new Veiculos();
          this.veiculo = new Veiculos();
}

  ngOnInit(): void {
  }
  
  onSubmit(){
    this.veiculos.usuario = this.idUsuario;
      this.veiculosService.alterarVeiculos(this.veiculos).subscribe(data =>{
        this.retornoVeiculos = data;
        if (this.retornoVeiculos != null){
          this.closePopup();
          alert('Alterado com sucesso!');
          this.token = '5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555';
          this.router.navigate(['/home'], { queryParams: { token: this.token, 'id': this.idUsuario  } });
        }
      })
    }

    openPopup() {
      this.displayStyle = "block";
      this.veiculosArg.id = this.idVeiculo;
      this.veiculosArg.usuario = this.idUsuario;
      this.veiculosService.recebeDadosAlterarVeiculo(this.veiculosArg).subscribe(data => {
        this.veiculo = data;
      });
    }

  displayStyle = "none";

  closePopup() {
    this.displayStyle = "none";
  }

  @Input() idVeiculo : string;
  @Input() idUsuario : string;

}
