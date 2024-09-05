import { TestBed } from '@angular/core/testing';

import { ApplicationUrlService } from './application-url.service';

describe('ApplicationUrlService', () => {
  let service: ApplicationUrlService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApplicationUrlService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
