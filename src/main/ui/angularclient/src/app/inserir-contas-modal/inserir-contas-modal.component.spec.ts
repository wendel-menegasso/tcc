import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InserirContasModalComponent } from './inserir-contas-modal.component';

describe('InserirContasModalComponent', () => {
  let component: InserirContasModalComponent;
  let fixture: ComponentFixture<InserirContasModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InserirContasModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InserirContasModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
