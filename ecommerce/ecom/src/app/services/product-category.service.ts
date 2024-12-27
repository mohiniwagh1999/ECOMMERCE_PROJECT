import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { ProductCategory } from '../common/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductCategoryService {

  constructor(private http:HttpClient) { }

  private apiUrl="http://localhost:8086/api/productCategory";

  getCategories(): Observable<ProductCategory[]>{

    return this.http.get<GetResponse>(this.apiUrl)
    .pipe(map(response => response._embedded.ProductCategory));

  }
}

interface GetResponse {
  _embedded: {
    ProductCategory: ProductCategory[];
  }

}