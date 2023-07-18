import { Component, OnInit } from '@angular/core';
import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ContasBancariasService } from '../service/contas-bancarias.service';
import { ContasBancarias } from '../model/contas-bancarias';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-inserir-contas-modal',
  templateUrl: './inserir-contas-modal.component.html',
  styleUrls: ['./inserir-contas-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class InserirContasModalComponent{

  contas: ContasBancarias;
  contasBancarias: any[] = [];
  contasCount = 0;

  constructor(config: NgbModalConfig, private modalService: NgbModal,
  private route: ActivatedRoute,
          private router: Router,
            private contasBancariasService: ContasBancariasService) {
    // customize default values of modals used by this component tree
    config.backdrop = 'static';
    config.keyboard = false;
          this.contas = new ContasBancarias();
  }

  open(content) {
    this.modalService.open(content);
  }

    onSubmit() {
      this.contasBancariasService.save(this.contas).subscribe(data => {
        this.contas = data;
        if (this.contas != null){
            this.gotoUserList();
        }
        });
    }

      gotoUserList() {
        this.router.navigate(['/home']);
      }

}
