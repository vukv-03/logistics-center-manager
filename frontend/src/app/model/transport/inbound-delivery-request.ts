import { Status } from "../helper/status";
import { ItemRequest } from "./item-request";

export interface InboundDeliveryRequest {
    arrivalDate: string;
    dockingTime: string;
    status: Status;
    customerId: number;
    items: ItemRequest[];
}