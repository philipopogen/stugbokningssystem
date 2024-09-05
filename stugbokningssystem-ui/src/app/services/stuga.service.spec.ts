import { TestBed } from '@angular/core/testing';

import { StugaService } from './stuga.service';

describe('StugaService', () => {
  let service: StugaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StugaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
