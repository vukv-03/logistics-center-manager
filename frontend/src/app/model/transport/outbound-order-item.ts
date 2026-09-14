import { MinimalProduct } from "../minimal-product";

export interface OutboundOrderItem {
  id?: number;
  product: MinimalProduct;
  quantity: number;
}