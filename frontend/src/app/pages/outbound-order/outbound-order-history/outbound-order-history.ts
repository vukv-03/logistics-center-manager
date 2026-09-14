import { Component, inject, signal } from '@angular/core';
import { OutboundOrder } from '../../../model/transport/outbound-order';
import { OutboundOrderService } from '../../../services/outbound-order-service';
import { OutboundOrderTable } from '../outbound-order-table/outbound-order-table';
import { RouterLink } from '@angular/router';
import { FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-outbound-order-history',
  imports: [OutboundOrderTable, RouterLink, ReactiveFormsModule],
  templateUrl: './outbound-order-history.html',
  styleUrl: './outbound-order-history.css',
})
export class OutboundOrderHistory {
  	orders = signal<OutboundOrder[]>([]);
	selectedOrder: OutboundOrder | null = null;
	searchControl = new FormControl<string>('');
	private outboundOrderService = inject(OutboundOrderService);

	ngOnInit() {
    	this.outboundOrderService.getHistory().subscribe(data => {
        	this.orders.set(data);
    	});
	}

	viewItems(order: OutboundOrder) {
		this.selectedOrder = order;
	}

	loadHistory() {
 		this.outboundOrderService.getHistory().subscribe(orders => {
      		this.orders.set(orders);
    	});
	}

	searchOrdersHistory() {
		const name = this.searchControl.value ?? '';

		if (name.trim() === '') {
			this.loadHistory();
			return;
		}

		this.outboundOrderService.search(name).subscribe(orders => {
			this.orders.set(orders);
		});
	}
}

