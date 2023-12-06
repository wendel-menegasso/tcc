import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InserirVeiculosComponent } from './inserir-veiculos.component';

describe('InserirVeiculosComponent', () => {
  let component: InserirVeiculosComponent;
  let fixture: ComponentFixture<InserirVeiculosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InserirVeiculosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InserirVeiculosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
