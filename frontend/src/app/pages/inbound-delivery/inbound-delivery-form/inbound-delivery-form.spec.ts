import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InboundDeliveryForm } from './inbound-delivery-form';

describe('InboundDeliveryForm', () => {
  let component: InboundDeliveryForm;
  let fixture: ComponentFixture<InboundDeliveryForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InboundDeliveryForm],
    }).compileComponents();

    fixture = TestBed.createComponent(InboundDeliveryForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
