import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InserirGastosModalComponent } from './inserir-gastos-modal.component';

describe('InserirGastosModalComponent', () => {
  let component: InserirGastosModalComponent;
  let fixture: ComponentFixture<InserirGastosModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InserirGastosModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InserirGastosModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
