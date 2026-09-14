import { Component, EventEmitter, inject, Input, OnChanges, OnInit, Output, signal, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../../../services/product-service';
import { CustomerService } from '../../../services/customer-service';
import { Customer } from '../../../model/users/customer';
import { ProductTypeService } from '../../../services/product-type-service';
import { ProductType } from '../../../model/helper/product-type';
import { Product } from '../../../model/product';
import { ProductRequest } from '../../../model/product-request';
import { MeasurementUnit } from '../../../model/helper/measurementUnit';

@Component({
  selector: 'app-product-form',
  imports: [ReactiveFormsModule],
  templateUrl: './product-form.html',
  styleUrl: './product-form.css',
})
export class ProductForm implements OnInit, OnChanges {
	private customerService = inject(CustomerService);
	private productTypeService = inject(ProductTypeService);
	private productService = inject(ProductService);
	customers = signal<Customer[]>([]);
	productTypes = signal<ProductType[]>([]);

	isEditing = false;
	editingProductId: number | null = null;

	@Input()
	productToEdit: Product | null = null;
	
	@Output()
	productCreated = new EventEmitter<void>();

	@Output()
	productChanged = new EventEmitter<void>();

	measurementUnits = [
		MeasurementUnit.GRAM,
		MeasurementUnit.KILOGRAM,
		MeasurementUnit.MILLILITER,
		MeasurementUnit.LITER,
		MeasurementUnit.PIECE
	];

	form = new FormGroup({
		name: new FormControl<string>(''),
		description: new FormControl<string>(''),
		measurementUnit: new FormControl<MeasurementUnit | null>(null),
		amountOfProduct: new FormControl<number | null>(null),
		productOwnerId: new FormControl<number | null>(null),
		productTypeId: new FormControl<number | null>(null)
	});

	resetForm() {
		this.form.reset();
		this.isEditing = false;
		this.editingProductId = null;
	}

	ngOnInit(): void {
		this.customerService.getAll().subscribe(c => {
			this.customers.set(c);
		})
		this.productTypeService.getAll().subscribe(pt => {
			this.productTypes.set(pt);
		})
	}

	ngOnChanges(): void {
    	if (this.productToEdit) {
			this.isEditing = true;
			this.editingProductId = this.productToEdit.id;

        	this.form.patchValue({
				name: this.productToEdit.name,
				description: this.productToEdit.description,
				measurementUnit: this.productToEdit.measurementUnit,
				amountOfProduct: this.productToEdit.amountOfProduct,
				productOwnerId: this.productToEdit.productOwnerId,
				productTypeId: this.productToEdit.productTypeId
        	});
    	}
	}		

	onAdd() {
		const product: ProductRequest = {
			name: this.form.value.name!,
			description: this.form.value.description!,
			measurementUnit: this.form.value.measurementUnit!,
			amountOfProduct: this.form.value.amountOfProduct!,
			productOwnerId: this.form.value.productOwnerId!,
			productTypeId: this.form.value.productTypeId!
		};

		this.productService.create(product).subscribe(product => {
			console.log(product);
			this.resetForm();
			this.productCreated.emit();  
		});
	}

	onChange() {
		const product: ProductRequest = {
			name: this.form.value.name!,
			description: this.form.value.description!,
			measurementUnit: this.form.value.measurementUnit!,
			amountOfProduct: this.form.value.amountOfProduct!,
			productOwnerId: this.form.value.productOwnerId!,
			productTypeId: this.form.value.productTypeId!
		};

		this.productService.update(this.editingProductId!, product).subscribe(product => {
			console.log(product);
			this.resetForm();
			this.productChanged.emit();
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