import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VeiculosUsuarioComponent } from './veiculos-usuario.component';

describe('VeiculosUsuarioComponent', () => {
  let component: VeiculosUsuarioComponent;
  let fixture: ComponentFixture<VeiculosUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VeiculosUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VeiculosUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
