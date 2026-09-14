import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InboundDeliveryPage } from './inbound-delivery-page';

describe('InboundDeliveryPage', () => {
  let component: InboundDeliveryPage;
  let fixture: ComponentFixture<InboundDeliveryPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InboundDeliveryPage],
    }).compileComponents();

    fixture = TestBed.createComponent(InboundDeliveryPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
