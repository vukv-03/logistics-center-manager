import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Address } from '../model/helper/address';

@Injectable({
  providedIn: 'root',
})
export class AddressService {
	public constructor(private http: HttpClient) {}

	getAll() {
		return this.http.get<Address[]>("/api/addresses");
	}

	getById(id: number) {
		return this.http.get<Address>(`/api/addresses/${id}`);
	}
}
