import { OutboundOrderItem } from "./outbound-order-item";
import { Status } from "../helper/status";
import { Address } from "../helper/address";

export interface OutboundOrder {
  id: number;
  createdAt: string;
  shippingDate: string;
  shippingTime: string;
  status: Status;
  deliveryAddress: Address;
  customerId: number;
  customer: string;
  items: OutboundOrderItem[];
}