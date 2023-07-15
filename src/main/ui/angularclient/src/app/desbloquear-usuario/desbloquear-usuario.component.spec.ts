import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DesbloquearUsuarioComponent } from './desbloquear-usuario.component';

describe('DesbloquearUsuarioComponent', () => {
  let component: DesbloquearUsuarioComponent;
  let fixture: ComponentFixture<DesbloquearUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DesbloquearUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DesbloquearUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
