import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InboundDeliveryHistory } from './inbound-delivery-history';

describe('InboundDeliveryHistory', () => {
  let component: InboundDeliveryHistory;
  let fixture: ComponentFixture<InboundDeliveryHistory>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InboundDeliveryHistory],
    }).compileComponents();

    fixture = TestBed.createComponent(InboundDeliveryHistory);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
