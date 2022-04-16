import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { Product } from '../model/product';
import { ProductSize } from '../model/data/product-size';
import { ProductFilter } from '../model/data/product-filter';
import { PageableProduct } from '../model/pageable-product';

const BASE_URL = '/api/products/'

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }


  getProductCards(productId: number): Observable<Product> {

    let url: string = BASE_URL + productId;
    return this.httpClient.get(url).pipe() as Observable<Product>;
  }

  getProductByNameAndSize(name: string, productSize: ProductSize): Observable<Product> {

    let size: string = ProductSize[productSize];
    let url: string = BASE_URL.slice(0, -1) + `?name=${name}&size=${size}`;
    return this.httpClient.get(url).pipe() as Observable<Product>;
  }

  getProductWithFilter(productFilter: ProductFilter, page: number): Observable<PageableProduct> {

    let filter: string = ProductFilter[productFilter];
    let url: string = BASE_URL.slice(0, -1) + `?filter=${filter}&page=${page}`;

    return this.httpClient.get(url).pipe() as Observable<PageableProduct>;
  }

  // getAllProducts
}
