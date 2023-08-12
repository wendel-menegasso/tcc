import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-alterar-rendas-modal',
  templateUrl: './alterar-rendas-modal.component.html',
  styleUrls: ['./alterar-rendas-modal.component.css']
})
export class AlterarRendasModalComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  @Input() idRenda : string;
}
