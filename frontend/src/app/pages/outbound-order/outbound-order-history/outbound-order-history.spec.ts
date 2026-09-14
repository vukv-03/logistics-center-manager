import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OutboundOrderHistory } from './outbound-order-history';

describe('OutboundOrderHistory', () => {
  let component: OutboundOrderHistory;
  let fixture: ComponentFixture<OutboundOrderHistory>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OutboundOrderHistory],
    }).compileComponents();

    fixture = TestBed.createComponent(OutboundOrderHistory);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
