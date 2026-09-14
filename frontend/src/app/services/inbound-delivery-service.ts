import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { InboundDelivery } from '../model/transport/inbound-delivery';
import { InboundDeliveryRequest } from '../model/transport/inbound-delivery-request';

@Injectable({
  providedIn: 'root',
})
export class InboundDeliveryService {
      public constructor(private http: HttpClient) {}

    getAll() {
      	return this.http.get<InboundDelivery[]>("http://localhost:8080/api/inbound_deliveries");
    }

    getById(id: number) {
    	  return this.http.get<InboundDelivery>(`http://localhost:8080/api/inbound_deliveries/${id}`);
    }

    create(inboundDelivery: InboundDeliveryRequest) {
    	  return this.http.post<InboundDelivery>("http://localhost:8080/api/inbound_deliveries", inboundDelivery);
    }

    update(id: number, inboundDelivery: InboundDeliveryRequest) {
    	  return this.http.put<InboundDelivery>(`http://localhost:8080/api/inbound_deliveries/${id}`, inboundDelivery);
    }

    delete(id: number) {
    	  return this.http.delete<InboundDelivery>(`http://localhost:8080/api/inbound_deliveries/${id}`);
    }

    search(name: string) {
        return this.http.get<InboundDelivery[]>(`http://localhost:8080/api/inbound_deliveries/search?customer=${encodeURIComponent(name)}`);
    }

    searchHistory(name: string) {
        return this.http.get<InboundDelivery[]>(`http://localhost:8080/api/inbound_deliveries/history/search?customer=${encodeURIComponent(name)}`);
    }

    getHistory() {
        return this.http.get<InboundDelivery[]>(`http://localhost:8080/api/inbound_deliveries/history`);
    }
}
