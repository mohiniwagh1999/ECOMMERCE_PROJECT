export class OrderItems {

    constructor(
        public imageUrl: string,
        public price: number,
        public name: string,
        public quantity: number,
        public productId: number
    ){}
}
