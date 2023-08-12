import { TestBed } from '@angular/core/testing';

import { RendasService } from './rendas-service';

describe('RendasServiceService', () => {
  let service: RendasService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RendasService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
