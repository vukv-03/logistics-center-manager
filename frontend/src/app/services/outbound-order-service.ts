import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OutboundOrder } from '../model/transport/outbound-order';
import { OutboundOrderRequest } from '../model/transport/outbound-order-request';

@Injectable({
  providedIn: 'root',
})
export class OutboundOrderService {
    public constructor(private http: HttpClient) {}

    getAll() {
      	return this.http.get<OutboundOrder[]>("/api/outbound_orders");
    }

    getById(id: number) {
      	return this.http.get<OutboundOrder>(`/api/outbound_orders/${id}`);
    }

    create(outboundOrder: OutboundOrderRequest) {
      	return this.http.post<OutboundOrder>("/api/outbound_orders", outboundOrder);
    }

    update(id: number, outboundOrder: OutboundOrderRequest) {
      	return this.http.put<OutboundOrder>(`/api/outbound_orders/${id}`, outboundOrder);
    }

    delete(id: number) {
      	return this.http.delete<OutboundOrder>(`/api/outbound_orders/${id}`);
    }

    search(name: string) {
        return this.http.get<OutboundOrder[]>(`/api/outbound_orders/search?customer=${encodeURIComponent(name)}`);
    }

    searchHistory(name: string) {
        return this.http.get<OutboundOrder[]>(`/api/outbound_orders/history/search?customer=${encodeURIComponent(name)}`);
    }

    getHistory() {
        return this.http.get<OutboundOrder[]>(`/api/outbound_orders/history`);
    }
}
