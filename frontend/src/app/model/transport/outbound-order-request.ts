import { Status } from "../helper/status";
import { ItemRequest } from "./item-request";

export interface OutboundOrderRequest {
	shippingDate: string;
	shippingTime: string;
	status: Status;
	deliveryAddress: {
		id: number;
	};
	customerId: number;
	items: ItemRequest[];
}