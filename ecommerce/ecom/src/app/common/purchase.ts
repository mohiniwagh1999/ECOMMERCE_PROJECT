import { Address } from "./address";
import { Customer } from "./customer";
import { OrderData } from "./order-data";
import { OrderItems } from "./order-items";

export class Purchase {
    constructor(
        public customer: Customer,
        public address: Address,
        public orderData: OrderData,
        public orderItems: OrderItems[]
      ) {}
}
