import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../model/users/customer';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  	public constructor(private http: HttpClient) {}

	getAll() {
		return this.http.get<Customer[]>("/api/customers");
	}

	getById(id: number) {
		return this.http.get<Customer>(`/api/customers/${id}`);
	}
}
