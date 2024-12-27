import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-paymentfail',
  standalone: true,
  imports: [],
  templateUrl: './paymentfail.component.html',
  styleUrl: './paymentfail.component.css'
})
export class PaymentfailComponent {
  constructor(private router: Router) {}

  retryPayment(): void {
    this.router.navigate(['/checkout']);  // Redirect to the checkout page
  }

}
