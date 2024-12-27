import { Component, EventEmitter, Output } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { ProductListComponent } from './product-list/product-list.component';
import { CommonModule } from '@angular/common';
import { ProductCategorytComponent } from './product-category/product-category.component';
import { FormsModule } from '@angular/forms';
import { CartService } from './services/cart.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,ProductListComponent,ProductCategorytComponent,CommonModule,RouterModule,CommonModule,FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ecom';
  currentProductName: string = '';


  constructor(public cartService: CartService){
    this.updateCartDetails();
  }

  @Output() productSearch: EventEmitter<string> = new EventEmitter<string>();  

 
  onSubmit(): void {
    console.log("app component product name:"+this.currentProductName);
    this.productSearch.emit(this.currentProductName); 
  }


  cartQuantity = 0;
  totalPrice = 0;

 

  ngOnInit(): void {
    this.cartService.totalQuantity$.subscribe(
      (quantity) => (this.cartQuantity = quantity)
    );

    this.cartService.totalPrice$.subscribe(
      (totalPrice) => (this.totalPrice = totalPrice)
    );
  }

  updateCartDetails() {
    this.cartQuantity = this.cartService.getTotalQuantity();
    this.totalPrice = this.cartService.getTotalPrice();
  }

  




}
