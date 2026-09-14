import { Component, EventEmitter, inject, Input, Output, signal } from '@angular/core';
import { CustomerService } from '../../../services/customer-service';
import { ProductService } from '../../../services/product-service';
import { InboundDeliveryService } from '../../../services/inbound-delivery-service';
import { Customer } from '../../../model/users/customer';
import { Product } from '../../../model/product';
import { Status } from '../../../model/helper/status';
import { InboundDelivery } from '../../../model/transport/inbound-delivery';
import { FormArray, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ItemRequest } from '../../../model/transport/item-request';
import { InboundDeliveryRequest } from '../../../model/transport/inbound-delivery-request';

type DeliveryItemForm = FormGroup<{
	productId: FormControl<number | null>;
	quantity: FormControl<number | null>;
}>;

@Component({
  selector: 'app-inbound-delivery-form',
  imports: [ReactiveFormsModule],
  templateUrl: './inbound-delivery-form.html',
  styleUrl: './inbound-delivery-form.css',
})
export class InboundDeliveryForm {
  	private customerService = inject(CustomerService);
	private inboundDeliveryService = inject(InboundDeliveryService);
	private productService = inject(ProductService);
	customers = signal<Customer[]>([]);
	products = signal<Product[]>([]);

	isEditing = false;
	editingDeliveryId: number | null = null;

	@Input()
	deliveryToEdit: InboundDelivery | null = null;
	
	@Output()
	deliveryCreated = new EventEmitter<void>();

	@Output()
	deliveryChanged = new EventEmitter<void>();

	status = [
		Status.PENDING,
		Status.PROCESSING,
		Status.COMPLETED
	];

	form = new FormGroup({
		arrivalDate: new FormControl<string>(''),
		dockingTime: new FormControl<string>(''),
		status: new FormControl<Status>(Status.PENDING),
		customerId: new FormControl<number | null>(null),
		items: new FormArray<DeliveryItemForm>([])
	});

	newItem(): DeliveryItemForm {
		return new FormGroup({
			productId: new FormControl<number | null>(null),
			quantity: new FormControl<number | null>(null)
		});
	}

	get items(): FormArray<DeliveryItemForm> {
		return this.form.controls.items;
	}

	addItem() {
		this.items.push(this.newItem());
	}

	removeItem(index: number) {
		this.items.removeAt(index);
	}

	resetForm() {
		this.form.reset({
			arrivalDate: '',
			dockingTime: '',
			status: Status.PENDING,
			customerId: null
		});

		this.items.clear();

		this.isEditing = false;
		this.editingDeliveryId = null;
	}

	ngOnInit(): void {
		this.customerService.getAll().subscribe(c => {
			this.customers.set(c);
		});

		this.productService.getAll().subscribe(p => {
			this.products.set(p);
		});
	}

	ngOnChanges(): void {
		if (this.deliveryToEdit) {
			this.isEditing = true;
			this.editingDeliveryId = this.deliveryToEdit.id;

			this.form.patchValue({
				arrivalDate: this.deliveryToEdit.arrivalDate,
				dockingTime: this.deliveryToEdit.dockingTime,
				status: this.deliveryToEdit.status,
				customerId: this.deliveryToEdit.customerId
			});

			this.items.clear();

			for (const item of this.deliveryToEdit.items) {
				const itemForm = new FormGroup({
					productId: new FormControl<number | null>(item.product.id),
					quantity: new FormControl<number | null>(item.quantity)
				});

				this.items.push(itemForm);
			}
		}
	}	

	onAdd() {
		const items: ItemRequest[] = this.items.controls.map(item => ({
			product: {
				id: item.controls.productId.value!
			},
			quantity: item.controls.quantity.value!
		}));

		const delivery: InboundDeliveryRequest = {
			arrivalDate: this.form.value.arrivalDate!,
			dockingTime: this.form.value.dockingTime!,
			status: Status.PENDING,
			customerId: this.form.value.customerId!,
			items: items
		};

		this.inboundDeliveryService.create(delivery).subscribe(delivery => {
			console.log(delivery);
			this.resetForm();
			this.deliveryCreated.emit();
		});
	}

	onChange() {
		const items: ItemRequest[] = this.items.controls.map(item => ({
			product: {
				id: item.controls.productId.value!
			},
			quantity: item.controls.quantity.value!
		}));
		
		const delivery: InboundDeliveryRequest = {
			arrivalDate: this.form.value.arrivalDate!,
			dockingTime: this.form.value.dockingTime!,
			status: this.form.value.status!,
			customerId: this.form.value.customerId!,
			items: items
		};

		this.inboundDeliveryService.update(this.editingDeliveryId!, delivery).subscribe(delivery => {
			console.log(delivery);
			this.resetForm();
			this.deliveryChanged.emit();
		});
	}

	onSubmit() {
    	if (this.isEditing) {
        	this.onChange();
    	} else {
        	this.onAdd();
		}	
	}
}
