import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OutboundOrderPage } from './outbound-order-page';

describe('OutboundOrderPage', () => {
  let component: OutboundOrderPage;
  let fixture: ComponentFixture<OutboundOrderPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OutboundOrderPage],
    }).compileComponents();

    fixture = TestBed.createComponent(OutboundOrderPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
