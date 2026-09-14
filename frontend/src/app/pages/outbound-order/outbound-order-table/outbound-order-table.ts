import { Component, EventEmitter, Input, Output } from '@angular/core';
import { OutboundOrder } from '../../../model/transport/outbound-order';

@Component({
  selector: 'app-outbound-order-table',
  imports: [],
  templateUrl: './outbound-order-table.html',
  styleUrl: './outbound-order-table.css',
})
export class OutboundOrderTable {
  	@Input()
	orders: OutboundOrder[] = [];

	@Output()
	editOrder = new EventEmitter<OutboundOrder>();

	@Output()
	deleteOrder = new EventEmitter<number>();

	@Output()
	viewItems = new EventEmitter<OutboundOrder>();

	@Input() 
	showActions: boolean = true;
}
