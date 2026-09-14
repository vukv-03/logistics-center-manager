import { MeasurementUnit } from "./helper/measurementUnit";

export interface Product {
    id: number;
    name: string;
    description: string;
    measurementUnit: MeasurementUnit;
    quantityInStock?: number;
    amountOfProduct: number;
    productOwnerId: number;
    productTypeId: number;
} 