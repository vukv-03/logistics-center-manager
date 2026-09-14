import { TestBed } from '@angular/core/testing';

import { OutboundOrderService } from './outbound-order-service';

describe('OutboundOrderService', () => {
  let service: OutboundOrderService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OutboundOrderService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
