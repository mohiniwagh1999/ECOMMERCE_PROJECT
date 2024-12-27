import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Observable } from 'rxjs/internal/Observable';
import { CartService } from '../services/cart.service';
import { CheckoutService } from '../services/checkout.service';
import { Router } from '@angular/router';
import { Purchase } from '../common/purchase';
import { Customer } from '../common/customer';
import { Address } from '../common/address';
import { OrderItems } from '../common/order-items';
import { OrderData } from '../common/order-data';
declare var Razorpay: any;

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent  implements OnInit{
  checkoutForm!: FormGroup;
  totalQuantity$!: Observable<number>; // Declare as Observable
  totalPrice$!: Observable<number>; 
  

  constructor(
    private fb: FormBuilder,
    private cartService: CartService,
    private checkoutService: CheckoutService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Initialize the checkout form
    this.checkoutForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      phoneNumber: ['', Validators.required],
      houseNo: ['', Validators.required],
      street: ['', Validators.required],
      city: ['', Validators.required],
      state: ['', Validators.required],
      zipcode: ['', Validators.required],
      country: ['', Validators.required]
    });


    this.totalQuantity$ = this.cartService.totalQuantity$;
    this.totalPrice$ = this.cartService.totalPrice$;

    // Subscribe to the values for debugging
    this.totalQuantity$.subscribe(quantity => {
      console.log("checkout component quantity:", quantity);
    });

    this.totalPrice$.subscribe(price => {
      console.log("checkout component price:", price);
    });
  }
 
  onSubmit() {
    if (this.checkoutForm.valid) {
      // Create Customer, Address, OrderItems, and OrderData
      const customer = new Customer(
        this.checkoutForm.value.name,
        
        this.checkoutForm.value.email,
        this.checkoutForm.value.phoneNumber
        
      );




      const address = new Address(
        this.checkoutForm.value.houseNo,
        this.checkoutForm.value.street,
        this.checkoutForm.value.city,
        this.checkoutForm.value.state,
        this.checkoutForm.value.zipcode,
        this.checkoutForm.value.country
      );



      const orderItems: OrderItems[] = this.cartService.getOrderItems();
      const totalQuantity = this.cartService.getTotalQuantity();
      const totalPrice = this.cartService.getTotalPrice();
      const orderData = new OrderData(totalQuantity, totalPrice);




      const purchase = new Purchase(customer, address, orderData, orderItems);

      console.log(purchase.customer.name);
      console.log(purchase.orderItems);



        // Call checkout service to place the order and get the Razorpay order ID
        this.checkoutService.placeOrder(purchase).subscribe(
          (response) => {
            const orderId = response.razorPayPaymentId; // Razorpay order ID
            const trackingNumber = response.orderTrackingNumber;
            this.openRazorpay(orderId, totalPrice,trackingNumber);
          },
          (error) => {
            console.error('Error placing order', error);
          }
        );
      } else {
        console.log('Form is invalid');
      }
    }

    openRazorpay(orderId: string, amount: number, trackingNumber: any) {
      const options = {
        key: 'rzp_test_pTmuAUtR5VyN5w', // Razorpay API Key
        amount: amount * 100, // Amount in paise (currency subunits)
        currency: 'INR',
        name: 'Your Company',
        description: 'Order Payment',
        order_id: orderId, // Razorpay order ID from the backend
        handler: (response: any) => {
          console.log('Payment successful:', response);
    
          const paymentDetails = {
            paymentId: response.razorpay_payment_id,
            orderId: response.razorpay_order_id,
            
            status: 'success', // This can be any status Razorpay returns or 'success'
            amount: amount
            
          };
          this.checkoutService.updateOrderWithPaymentStatus(paymentDetails).subscribe(
            (res) => {
              console.log('Order status updated successfully:', res);
    
              // Clear the cart and redirect to order success page
              this.cartService.clearCart();
             
              this.router.navigate(['/order-success'], { state: { trackingNumber } });
            },
            (error) => {
              console.error('Error updating order status:', error);
            }
          );
        },
    
        prefill: {
          name: this.checkoutForm.value.name,
          email: this.checkoutForm.value.email,
          contact: this.checkoutForm.value.phone
        },
        theme: {
          color: '#F37254'
        }
      };
      const razorpay = new  Razorpay(options);
      razorpay.on('payment.failed', (response: any) => {
        console.log('Payment failed:', response);
        
        // Redirect to Payment Failure page
        this.router.navigate(['/payment-failure']);
      });
      razorpay.open();
    }
    

}


// openRazorpay(orderId: string, amount: number, trackingNumber: any) {
//   const options = {
//     key: 'rzp_test_pTmuAUtR5VyN5w', // Razorpay API Key
//     amount: amount * 100, // Amount in paise (currency subunits)
//     currency: 'INR',
//     name: 'Your Company',
//     description: 'Order Payment',
//     order_id: orderId, // Razorpay order ID from the backend
//     handler: (response: any) => {
//       console.log('Payment successful:', response);

//       const paymentDetails = {
//         paymentId: response.razorpay_payment_id,
//         orderId: response.razorpay_order_id,
//         status: 'success', // This can be any status Razorpay returns or 'success'
//         amount: amount
//       };
//       this.checkoutService.updateOrderWithPaymentStatus(paymentDetails).subscribe(
//         (res) => {
//           console.log('Order status updated successfully:', res);

//           // Clear the cart and redirect to order success page
//           this.cartService.clearCart();
         
//           this.router.navigate(['/order-success'], { state: { trackingNumber } });
//         },
//         (error) => {
//           console.error('Error updating order status:', error);
//         }
//       );
//     },

//     prefill: {
//       name: this.checkoutForm.value.name,
//       email: this.checkoutForm.value.email,
//       contact: this.checkoutForm.value.phone
//     },
//     theme: {
//       color: '#F37254'
//     }
//   };
//   const razorpay = new razorpay(options);
//   razorpay.on('payment.failed', (response: any) => {
//     console.log('Payment failed:', response);
    
//     // Redirect to Payment Failure page
//     this.router.navigate(['/payment-failure']);
//   });
//   razorpay.open();
// }

