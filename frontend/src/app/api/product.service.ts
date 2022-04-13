import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Product } from '../model/product';

const BASE_URL = '/api/products/'

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  /*
  getProductById(productId: number): Observable<Product> {

    let url = BASE_URL + productId;

    return this.httpClient.get(url).pipe(
      map(response => )
    );

  }
  */

  // getProductByNameAndSize

  // getAllProductsPaginated

  // getAllProducts
}
