import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Purchase } from '../common/purchase';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  private checkoutUrl = 'http://localhost:8085/api/checkdetails'; // Replace with your API URL
  private paymentUpdateUrl = 'http://localhost:8085/update-payment-status';

  constructor(private http: HttpClient) { }


   // Method to place an order
   placeOrder(
    purchase : Purchase
  ): Observable<any> {
    console.log("checkout service details:"+purchase.customer.name);
    console.log("order total price:"+purchase.orderData.totalPrice);
    return this.http.post<any>(this.checkoutUrl, purchase);
  }
  updateOrderWithPaymentStatus(paymentDetails: any) {
    return this.http.post(this.paymentUpdateUrl, paymentDetails);
  }



}
