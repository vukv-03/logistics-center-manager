import { Component, EventEmitter, inject, Input, Output, signal } from '@angular/core';
import { CustomerService } from '../../../services/customer-service';
import { OutboundOrderService } from '../../../services/outbound-order-service';
import { Customer } from '../../../model/users/customer';
import { OutboundOrder } from '../../../model/transport/outbound-order';
import { FormArray, FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { AddressService } from '../../../services/address-service';
import { OutboundOrderRequest } from '../../../model/transport/outbound-order-request';
import { ItemRequest } from '../../../model/transport/item-request';
import { Address } from '../../../model/helper/address';
import { ProductService } from '../../../services/product-service';
import { Product } from '../../../model/product';
import { Status } from '../../../model/helper/status';

type OrderItemForm = FormGroup<{
	productId: FormControl<number | null>;
	quantity: FormControl<number | null>;
}>;

@Component({
  selector: 'app-outbound-order-form',
  imports: [ReactiveFormsModule],
  templateUrl: './outbound-order-form.html',
  styleUrl: './outbound-order-form.css',
})
export class OutboundOrderForm {
  	private customerService = inject(CustomerService);
	private outboundOrderService = inject(OutboundOrderService);
	private addressService = inject(AddressService);
	private productService = inject(ProductService);
	customers = signal<Customer[]>([]);
	addresses = signal<Address[]>([]);
	products = signal<Product[]>([]);

	isEditing = false;
	editingOrderId: number | null = null;

	@Input()
	orderToEdit: OutboundOrder | null = null;
	
	@Output()
	orderCreated = new EventEmitter<void>();

	@Output()
	orderChanged = new EventEmitter<void>();

	status = [
		Status.PENDING,
		Status.PROCESSING,
		Status.COMPLETED
	];

	form = new FormGroup({
		shippingDate: new FormControl<string>(''),
		shippingTime: new FormControl<string>(''),
		status: new FormControl<Status>(Status.PENDING),
		deliveryAddressId: new FormControl<number | null>(null),
		customerId: new FormControl<number | null>(null),
		items: new FormArray<OrderItemForm>([])
	});

	newItem(): OrderItemForm {
		return new FormGroup({
			productId: new FormControl<number | null>(null),
			quantity: new FormControl<number | null>(null)
		});
	}

	get items(): FormArray<OrderItemForm> {
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
			shippingDate: '',
			shippingTime: '',
			status: Status.PENDING,
			deliveryAddressId: null,
			customerId: null
		});

		this.items.clear();

		this.isEditing = false;
		this.editingOrderId = null;
	}

	ngOnInit(): void {
		this.customerService.getAll().subscribe(c => {
			this.customers.set(c);
		});

		this.addressService.getAll().subscribe(a => {
			this.addresses.set(a);
		});

		this.productService.getAll().subscribe(p => {
			this.products.set(p);
		});
	}

	ngOnChanges(): void {
		if (this.orderToEdit) {
			this.isEditing = true;
			this.editingOrderId = this.orderToEdit.id;

			this.form.patchValue({
				shippingDate: this.orderToEdit.shippingDate,
				shippingTime: this.orderToEdit.shippingTime,
				status: this.orderToEdit.status,
				deliveryAddressId: this.orderToEdit.deliveryAddress.id,
				customerId: this.orderToEdit.customerId
			});

			this.items.clear();

			for (const item of this.orderToEdit.items) {
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

		const order: OutboundOrderRequest = {
			shippingDate: this.form.value.shippingDate!,
			shippingTime: this.form.value.shippingTime!,
			status: Status.PENDING,
			customerId: this.form.value.customerId!,

			deliveryAddress: {
				id: this.form.value.deliveryAddressId!
			},

			items: items
		};

		this.outboundOrderService.create(order).subscribe(order => {
			console.log(order);
			this.resetForm();
			this.orderCreated.emit();
		});
	}

	onChange() {
		const items: ItemRequest[] = this.items.controls.map(item => ({
			product: {
				id: item.controls.productId.value!
			},
			quantity: item.controls.quantity.value!
		}));
		
		const order: OutboundOrderRequest = {
			shippingDate: this.form.value.shippingDate!,
			shippingTime: this.form.value.shippingTime!,
			status: this.form.value.status!,
			customerId: this.form.value.customerId!,

			deliveryAddress: {
				id: this.form.value.deliveryAddressId!
			},

			items: items
		};

		this.outboundOrderService.update(this.editingOrderId!, order).subscribe(order => {
			console.log(order);
			this.resetForm();
			this.orderChanged.emit();
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
