import { InboundDeliveryItem } from "./inbound-delivery-item";
import { Status } from "../helper/status";

export interface InboundDelivery {
  id: number;
  createdAt: string;
  arrivalDate: string;
  dockingTime: string;
  status: Status;
  customerId: number;
  customer: string;
  items: InboundDeliveryItem[];
}