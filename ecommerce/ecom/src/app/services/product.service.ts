import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../common/product';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = "http://localhost:8086/api/product";
  httpClient: any;

  constructor(private http: HttpClient) { }

  getProductsByCategory(theCategoryId: number): Observable<Product[]>{
    const searchUrl = `${this.apiUrl}/search/findByCategoryId?id=${theCategoryId}`;
    return this.http.get<GetResponse>(searchUrl)
                          .pipe(map(response=> response._embedded.Product));
}

searchProduct(theKeyword:string):Observable<Product[]>{
  console.log("productService:"+theKeyword);
  const searchUrl=`${this.apiUrl}/search/findByNameContaining?name=${theKeyword}`;
  return this.http.get<GetResponse>(searchUrl)
               .pipe(map(response=>response._embedded.Product));
}



  getProducts(): Observable<Product[]> {

    return this.http.get<GetResponse>(this.apiUrl)
      .pipe(map(response => response._embedded.Product));

  }

  getProduct(theProductId:number): Observable<Product>{
    const productUrl = `${this.apiUrl}/${theProductId}`;
    return this.http.get<Product>(productUrl);
}


}


interface GetResponse {
  _embedded: {
    Product: Product[];
  }
}
