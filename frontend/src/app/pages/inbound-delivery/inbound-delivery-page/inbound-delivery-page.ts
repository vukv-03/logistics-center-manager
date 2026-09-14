import { Component, Inject, inject, signal } from '@angular/core';
import { InboundDeliveryService } from '../../../services/inbound-delivery-service';
import { InboundDelivery } from '../../../model/transport/inbound-delivery';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { InboundDeliveryTable } from '../inbound-delivery-table/inbound-delivery-table';
import { InboundDeliveryForm } from '../inbound-delivery-form/inbound-delivery-form';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-inbound-delivery-page',
  imports: [ReactiveFormsModule, InboundDeliveryTable, InboundDeliveryForm, RouterLink],
  templateUrl: './inbound-delivery-page.html',
  styleUrl: './inbound-delivery-page.css',
})
export class InboundDeliveryPage {
    private inboundDeliveryService = inject(InboundDeliveryService);
    deliveries = signal<InboundDelivery[]>([]);
    deliveryToEdit: InboundDelivery | null = null;
    searchControl = new FormControl<string>('');
    selectedDelivery: InboundDelivery | null = null;
    
    ngOnInit() {
        this.loadDeliveries();
    }

    loadDeliveries() {
      this.inboundDeliveryService.getAll().subscribe(deliveries => {
          this.deliveries.set(deliveries.filter(deliveries =>
                deliveries.status !== 'COMPLETED'
            ));
      });
    }

    startEditing(product: InboundDelivery) {
      this.deliveryToEdit = product;
    }

    deleteDelivery(id: number) {
      this.inboundDeliveryService.delete(id).subscribe(() => {
            this.loadDeliveries();
      this.selectedDelivery = null;
      });
    }

    searchDeliveries() {
        const name = this.searchControl.value ?? '';

        if (name.trim() === '') {
          this.loadDeliveries();
          return;
        }

        this.inboundDeliveryService.search(name).subscribe(deliveries => {
          this.deliveries.set(deliveries);
        });
    }
    
    viewItems(order: InboundDelivery) {
      this.selectedDelivery = order;
    }
}
