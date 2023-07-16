import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ContasBancariasService } from '../service/contas-bancarias.service';
import { ContasBancarias } from '../model/contas-bancarias';

@Component({
  selector: 'app-contas-bancarias',
  templateUrl: './contas-bancarias.component.html',
  styleUrls: ['./contas-bancarias.component.css']
})
export class ContasBancariasComponent implements OnInit {

  contas: ContasBancarias;

  constructor(
    private route: ActivatedRoute,
      private router: Router,
        private contasBancariasService: ContasBancariasService) {
    this.contas = new ContasBancarias();
  }

  ngOnInit(): void {
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
