import { Component, OnInit } from '@angular/core';

import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { ProductCategory } from '../common/product-category';
import { ProductCategoryService } from '../services/product-category.service';

@Component({
  selector: 'app-product-category',
  standalone: true,
  imports: [FormsModule,CommonModule,RouterLink,RouterLinkActive],
  templateUrl: './product-category.component.html',
  styleUrl: './product-category.component.css'
})
export class ProductCategorytComponent implements OnInit{


  categories:ProductCategory[]=[];

  constructor(private categoryService:ProductCategoryService){}

  ngOnInit(): void {
      this.getAllCategories();
  }

  getAllCategories(){
    this.categoryService.getCategories().subscribe(data=>{
      this.categories=data;
    })
  }
}