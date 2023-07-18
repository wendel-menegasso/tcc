import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContasBancariasService } from '../service/contas-bancarias.service';
import { ContasBancarias } from '../model/contas-bancarias';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-contas-bancarias',
  templateUrl: './contas-bancarias.component.html',
  styleUrls: ['./contas-bancarias.component.css']
})
export class ContasBancariasComponent implements OnInit {

  contas: ContasBancarias;
  contasBancarias: any[] = [];
  contasCount = 0;
    destroy$: Subject<boolean> = new Subject<boolean>();

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private contasBancariasService: ContasBancariasService) {
    this.contas = new ContasBancarias();
  }

  ngOnInit(): void {
    this.getTodasContas();
  }

    getTodasContas() {
      this.contasBancariasService.findAll().pipe(takeUntil(this.destroy$)).subscribe((contasBancarias: any[]) => {
  		    this.contasCount = contasBancarias.length;
          this.contasBancarias = contasBancarias;
      });
    }
    ngOnDestroy() {
      this.destroy$.next(true);
      this.destroy$.unsubscribe();
    }

}
