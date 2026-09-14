import { Injectable } from '@angular/core';
import { ProductType } from '../model/helper/product-type';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ProductTypeService {
    public constructor(private http: HttpClient) {}

	getAll() {
		return this.http.get<ProductType[]>("http://localhost:8080/api/product_types");
	}

	getById(id: number) {
		return this.http.get<ProductType>(`http://localhost:8080/api/product_types/${id}`);
	}
}
