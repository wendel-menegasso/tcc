import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImoveisUsuarioComponent } from './imoveis-usuario.component';

describe('ImoveisUsuarioComponent', () => {
  let component: ImoveisUsuarioComponent;
  let fixture: ComponentFixture<ImoveisUsuarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImoveisUsuarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImoveisUsuarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
