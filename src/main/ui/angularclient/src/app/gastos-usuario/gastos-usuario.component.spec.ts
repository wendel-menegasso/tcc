import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GastosUsuarioComponent } from './gastos-usuario.component';

describe('GastosUsuarioComponent', () => {
  let component: GastosUsuarioComponent;
  let fixture: ComponentFixture<GastosUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GastosUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GastosUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
