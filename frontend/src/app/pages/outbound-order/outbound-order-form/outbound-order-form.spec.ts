import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OutboundOrderForm } from './outbound-order-form';

describe('OutboundOrderForm', () => {
  let component: OutboundOrderForm;
  let fixture: ComponentFixture<OutboundOrderForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OutboundOrderForm],
    }).compileComponents();

    fixture = TestBed.createComponent(OutboundOrderForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
