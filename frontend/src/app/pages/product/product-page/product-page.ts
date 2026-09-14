import { Component, inject, OnInit, signal } from '@angular/core';
import { ProductForm } from '../product-form/product-form';
import { ProductTable } from '../product-table/product-table';
import { Product } from '../../../model/product';
import { ProductService } from '../../../services/product-service';
import { FormControl, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-product-page',
  imports: [ReactiveFormsModule, ProductForm, ProductTable],
  templateUrl: './product-page.html',
  styleUrl: './product-page.css',
})
export class ProductPage implements OnInit {
	private productService = inject(ProductService);
	products = signal<Product[]>([]);
	productToEdit: Product | null = null;
	searchControl = new FormControl<string>('');
	
	ngOnInit() {
  		this.loadProducts();
	}

  	loadProducts() {
 		this.productService.getAll().subscribe(products => {
      		this.products.set(products);
    	});
	}

	startEditing(product: Product) {
    	this.productToEdit = product;
	}

	deleteProduct(id: number) {
    	this.productService.delete(id).subscribe(() => {
            this.loadProducts();
  		});
	}

	searchProducts() {
		const name = this.searchControl.value ?? '';

		if (name.trim() === '') {
			this.loadProducts();
			return;
		}

		this.productService.search(name).subscribe(products => {
			this.products.set(products);
		});
	}	
}
