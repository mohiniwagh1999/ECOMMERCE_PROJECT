import { Component } from '@angular/core';
import { CartService } from '../services/cart.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  cartItems: any[] = [];
  totalPrice = 0;

  constructor(private cartService: CartService, private router: Router) {}

  ngOnInit(): void {
    console.log('CartComponent loaded');
    this.cartItems = this.cartService.getCartItems();
    this.totalPrice = this.cartService.getTotalPrice();
  }

  updateQuantity(id: number, quantity: number) {
    this.cartService.updateItemQuantity(id, quantity);
    this.loadCartDetails(); // Refresh cart items and total price after removing
  }

  removeItem(item: any) {
    this.cartService.removeFromCart(item.id);
    this.loadCartDetails(); // Refresh cart items and total price after removing
  }

  loadCartDetails() {
    this.cartItems = this.cartService.getCartItems();
    this.totalPrice = this.cartService.getTotalPrice();
    console.log("CartComponent"+this.totalPrice);
  }

  getTotalQuantity(): number {
    return this.cartItems.reduce((total, item) => total + item.quantity, 0);
  }
  proceedToCheckout(){
    this.router.navigate(['/checkout']);
  }
}