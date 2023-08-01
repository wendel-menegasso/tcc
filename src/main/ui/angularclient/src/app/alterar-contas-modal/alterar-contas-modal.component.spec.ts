import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlterarContasModalComponent } from './alterar-contas-modal.component';

describe('AlterarContasModalComponent', () => {
  let component: AlterarContasModalComponent;
  let fixture: ComponentFixture<AlterarContasModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlterarContasModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlterarContasModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
