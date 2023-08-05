import { TestBed } from '@angular/core/testing';

import { RendasServiceService } from './rendas-service.service';

describe('RendasServiceService', () => {
  let service: RendasServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RendasServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
