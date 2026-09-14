import { Component, EventEmitter, Input, Output } from '@angular/core';
import { InboundDelivery } from '../../../model/transport/inbound-delivery';

@Component({
  selector: 'app-inbound-delivery-table',
  imports: [],
  templateUrl: './inbound-delivery-table.html',
  styleUrl: './inbound-delivery-table.css',
})
export class InboundDeliveryTable {
    @Input()
    deliveries: InboundDelivery[] = [];

    @Output()
    editDelivery = new EventEmitter<InboundDelivery>();

    @Output()
    deleteDelivery = new EventEmitter<number>();

    @Output()
    viewItems = new EventEmitter<InboundDelivery>();

    @Input() 
	  showActions: boolean = true;
}
