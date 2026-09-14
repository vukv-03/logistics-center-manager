import { MinimalProduct } from "../minimal-product";

export interface InboundDeliveryItem {
  id?: number;
  product: MinimalProduct;
  quantity: number;
}