import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { Product } from '../common/product';
import { ProductService } from '../services/product.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { CartService } from '../services/cart.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [FormsModule,CommonModule,RouterLink],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit { 

  products: Product[] = [];
  currentCategoryId: number = 1;
  @Input() currentProductName: string = '';

  product!: Product;

  constructor(private productService: ProductService,
    private route: ActivatedRoute,private cartService: CartService) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params=>{
      const productName=params.get('name');
      if(productName)
      {
        this.onProductSearch(productName);
      }
      else{
        this.listProducts();
      }
    });

  }

  addToCart(product: any) {
    const productToAdd = { ...product, quantity: 1 };  // Set default quantity to 1
    this.cartService.addToCart(productToAdd);
  }

  ngOnChanges(changes: SimpleChanges): void {
    // console.log('Changes detected:', changes);
    if (changes['currentProductName'] && this.currentProductName) {
      this.onProductSearch(this.currentProductName);  
    }
  }

  onProductSearch(term: string): void {
    console.log(term);
    this.productService.searchProduct(term)
      .subscribe((data: Product[]) => {
        this.products = data;
      }, (error) => {
        console.error('Error fetching search results:', error);
      });
  }

  listProducts(){
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');
    if (hasCategoryId) {
      // get category id and convert it into num type
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
    } else {
      this.currentCategoryId = 1;
    }
    this.productService.getProductsByCategory(this.currentCategoryId).subscribe(data => {
      this.products = data;
    })
  }
  

  





}

