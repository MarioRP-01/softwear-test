import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';

import { Product } from '@app/shared/model/product';
import { ProductSize } from '@app/shared/data-type/product-size';
import { ProductFilter } from '@app/shared/data-type/product-filter';
import { PageableProduct } from '@app/shared/model/pageable-product';

const BASE_URL = '/api/products'

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  getProductById(id: number) {

    let url: string =  BASE_URL + `/${id}`;
    
    return this.httpClient.get(url).pipe() as Observable<Product>
  }

  getProductByNameAndSize(name: string, productSize: ProductSize): Observable<Product> {

    let size: string = ProductSize[productSize];
    let url: string = BASE_URL + `?name=${name}&size=${size}`;
    
    return this.httpClient.get(url).pipe() as Observable<Product>;
  }

  getProductWithFilter(productFilter: ProductFilter, page: number): Observable<PageableProduct> {

    let url: string = BASE_URL + `?filter=${productFilter}&page=${page}`;

    return this.httpClient.get(url).pipe() as Observable<PageableProduct>;
  }

  //getProductSizeAvailability

  // getAllProducts
}
