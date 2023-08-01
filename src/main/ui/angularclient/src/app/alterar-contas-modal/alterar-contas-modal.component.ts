import { Component, Input, OnInit } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ContasBancariasService } from '../service/contas-bancarias.service';
import { ContasBancarias } from '../model/contas-bancarias';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-alterar-contas-modal',
  templateUrl: './alterar-contas-modal.component.html',
  styleUrls: ['./alterar-contas-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class AlterarContasModalComponent implements OnInit {

  conta: ContasBancarias;
  contasArg: ContasBancarias;
  contasBancarias: any[] = [];
  contasCount = 0;

  constructor(private route: ActivatedRoute,
          private router: Router,
            private contasBancariasService: ContasBancariasService) {
          this.conta = new ContasBancarias();
          this.contasArg = new ContasBancarias();
  }

  ngOnInit(): void {
  }

  onSubmit(){
  }

  displayStyle = "none";

  openPopup() {
    this.displayStyle = "block";
    this.contasArg.id = this.idConta;
    this.contasBancariasService.alterar(this.contasArg).subscribe(data => {
      this.conta = data;
      alert(this.conta.id);
      });
  }
  closePopup() {
    this.displayStyle = "none";
  }

  gotoAlterarConta() {
    this.router.navigate(['/home']);
  }

  @Input() idConta : string;

}
