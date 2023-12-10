import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlterarImoveisComponent } from './alterar-imoveis.component';

describe('AlterarImoveisComponent', () => {
  let component: AlterarImoveisComponent;
  let fixture: ComponentFixture<AlterarImoveisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlterarImoveisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlterarImoveisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
