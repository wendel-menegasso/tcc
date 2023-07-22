import { NgbModalConfig, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ContasBancariasService } from '../service/contas-bancarias.service';
import { ContasBancarias } from '../model/contas-bancarias';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from "@angular/core";

@Component({
  selector: 'app-inserir-contas-modal',
  templateUrl: './inserir-contas-modal.component.html',
  styleUrls: ['./inserir-contas-modal.component.css'],
  providers: [NgbModalConfig, NgbModal]
})
export class InserirContasModalComponent implements OnInit{

  contas: ContasBancarias;
  contasBancarias: any[] = [];
  contasCount = 0;

  constructor(private route: ActivatedRoute,
          private router: Router,
            private contasBancariasService: ContasBancariasService) {
          this.contas = new ContasBancarias();
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
        displayStyle = "none";

        openPopup() {
          this.displayStyle = "block";
        }
        closePopup() {
          this.displayStyle = "none";
        }
          ngOnInit() {}
}
