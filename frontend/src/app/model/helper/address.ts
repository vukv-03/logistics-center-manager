import { City } from "./city";

export interface Address {
  id: number;
  street: string;
  buildingNumber: number;
  city: City;
}