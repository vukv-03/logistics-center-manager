import { Component, inject, signal } from '@angular/core';
import { InboundDeliveryTable } from '../inbound-delivery-table/inbound-delivery-table';
import { InboundDeliveryService } from '../../../services/inbound-delivery-service';
import { InboundDelivery } from '../../../model/transport/inbound-delivery';
import { RouterLink } from '@angular/router';
import { FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-inbound-delivery-history',
  imports: [InboundDeliveryTable, RouterLink, ReactiveFormsModule],
  templateUrl: './inbound-delivery-history.html',
  styleUrl: './inbound-delivery-history.css',
})
export class InboundDeliveryHistory {
    deliveries = signal<InboundDelivery[]>([]);
    selectedDelivery: InboundDelivery | null = null;
    searchControl = new FormControl<string>('');
    private inboundDeliveryService = inject(InboundDeliveryService);

    ngOnInit() {
        this.inboundDeliveryService.getHistory().subscribe(deliveries => {
            this.deliveries.set(deliveries);
        });
    }

    viewItems(delivery: InboundDelivery) {
        this.selectedDelivery = delivery;
    }
    
    loadHistory() {
 		this.inboundDeliveryService.getHistory().subscribe(deliveries => {
      		this.deliveries.set(deliveries);
    	});
	}

	searchDeliveriesHistory() {
		const name = this.searchControl.value ?? '';

		if (name.trim() === '') {
			this.loadHistory();
			return;
		}

		this.inboundDeliveryService.searchHistory(name).subscribe(deliveries => {
			this.deliveries.set(deliveries);
		});
	}
}
