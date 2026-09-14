import { Component, EventEmitter, inject, Input, OnInit, Output, signal } from '@angular/core';
import { Product } from '../../../model/product';

@Component({
	selector: 'app-product-table',
	imports: [],
	templateUrl: './product-table.html',
	styleUrl: './product-table.css',
})
export class ProductTable {
	@Input()
	products: Product[] = [];

	@Output()
	editProduct = new EventEmitter<Product>();

	@Output()
	deleteProduct = new EventEmitter<number>();
}
