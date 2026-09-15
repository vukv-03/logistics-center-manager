import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../model/product';
import { ProductRequest } from '../model/product-request';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
	public constructor(private http: HttpClient) {}

	getAll() {
		return this.http.get<Product[]>("/api/products");
	}

	getById(id: number) {
		return this.http.get<Product>(`/api/products/${id}`);
	}

	create(product: ProductRequest) {
		return this.http.post<ProductRequest>("/api/products", product);
	}

	update(id: number, product: ProductRequest) {
		return this.http.put<ProductRequest>(`/api/products/${id}`, product);
	}

	delete(id: number) {
		return this.http.delete<Product>(`/api/products/${id}`);
	}

	search(name: string) {
		return this.http.get<Product[]>(`/api/products/search?name=${encodeURIComponent(name)}`);
	}
}
