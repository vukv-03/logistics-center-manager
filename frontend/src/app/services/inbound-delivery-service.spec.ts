import { TestBed } from '@angular/core/testing';

import { InboundDeliveryService } from './inbound-delivery-service';

describe('InboundDeliveryService', () => {
  let service: InboundDeliveryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InboundDeliveryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
