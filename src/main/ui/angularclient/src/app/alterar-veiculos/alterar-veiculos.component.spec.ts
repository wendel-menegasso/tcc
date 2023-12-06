import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlterarVeiculosComponent } from './alterar-veiculos.component';

describe('AlterarVeiculosComponent', () => {
  let component: AlterarVeiculosComponent;
  let fixture: ComponentFixture<AlterarVeiculosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlterarVeiculosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlterarVeiculosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
