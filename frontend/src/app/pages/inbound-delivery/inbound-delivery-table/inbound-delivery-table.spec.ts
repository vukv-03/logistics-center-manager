import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InboundDeliveryTable } from './inbound-delivery-table';

describe('InboundDeliveryTable', () => {
  let component: InboundDeliveryTable;
  let fixture: ComponentFixture<InboundDeliveryTable>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InboundDeliveryTable],
    }).compileComponents();

    fixture = TestBed.createComponent(InboundDeliveryTable);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
