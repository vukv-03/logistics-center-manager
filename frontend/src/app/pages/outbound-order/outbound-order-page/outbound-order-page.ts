import { Component, inject, signal } from '@angular/core';
import { OutboundOrderService } from '../../../services/outbound-order-service';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { OutboundOrder } from '../../../model/transport/outbound-order';
import { OutboundOrderTable } from '../outbound-order-table/outbound-order-table';
import { OutboundOrderForm } from '../outbound-order-form/outbound-order-form';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-outbound-order-page',
  imports: [ReactiveFormsModule, OutboundOrderTable, OutboundOrderForm, RouterLink],
  templateUrl: './outbound-order-page.html',
  styleUrl: './outbound-order-page.css',
})
export class OutboundOrderPage {
  	private outboundOrderService = inject(OutboundOrderService);
	orders = signal<OutboundOrder[]>([]);
	orderToEdit: OutboundOrder | null = null;
	searchControl = new FormControl<string>('');
	selectedOrder: OutboundOrder | null = null;
	
	ngOnInit() {
  		this.loadOrders();
	}

  	loadOrders() {
 		this.outboundOrderService.getAll().subscribe(orders => {
      		this.orders.set(orders.filter(order =>
                order.status !== 'COMPLETED'
            ));
    	});
	}

	startEditing(product: OutboundOrder) {
    	this.orderToEdit = product;
	}

	deleteOrder(id: number) {
    	this.outboundOrderService.delete(id).subscribe(() => {
            this.loadOrders();
			this.selectedOrder = null;
  		});
	}

	searchOrders() {
		const name = this.searchControl.value ?? '';

		if (name.trim() === '') {
			this.loadOrders();
			return;
		}

		this.outboundOrderService.search(name).subscribe(orders => {
			this.orders.set(orders);
		});
	}
	
	viewItems(order: OutboundOrder) {
		this.selectedOrder = order;
	}
}
