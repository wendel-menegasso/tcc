import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InserirImoveisComponent } from './inserir-imoveis.component';

describe('InserirImoveisComponent', () => {
  let component: InserirImoveisComponent;
  let fixture: ComponentFixture<InserirImoveisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InserirImoveisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InserirImoveisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
