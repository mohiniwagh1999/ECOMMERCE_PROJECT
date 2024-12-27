import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ordersuccess',
  standalone: true,
  imports: [],
  templateUrl: './ordersuccess.component.html',
  styleUrl: './ordersuccess.component.css'
})
export class OrdersuccessComponent  implements OnInit{

  trackingNumber: string = '';

  constructor(private router: Router) {}

  ngOnInit(): void {
    // Assuming the tracking number is passed through router state
    const state = history.state;
    this.trackingNumber = state.trackingNumber || 'N/A';
  }

  continueShopping(): void {
    this.router.navigate(['/products']);  // Redirect to shop page or home page
  }

}
