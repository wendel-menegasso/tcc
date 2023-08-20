import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlterarGastosModalComponent } from './alterar-gastos-modal.component';

describe('AlterarGastosModalComponent', () => {
  let component: AlterarGastosModalComponent;
  let fixture: ComponentFixture<AlterarGastosModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlterarGastosModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlterarGastosModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
