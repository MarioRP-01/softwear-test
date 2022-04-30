import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Route, Router, RouterStateSnapshot, UrlSegment, UrlTree } from '@angular/router';
import { Product } from '@app/shared/model';
import { catchError, map, Observable, of } from 'rxjs';
import { ProductService } from '../api';

@Injectable({
  providedIn: 'root'
})
export class ProductGuard implements CanActivate {

  constructor(
    private productService: ProductService,
    private router: Router
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      
    const id: number = route.params['productId'];

    return this.productService.getProductById(id).pipe(map(
      response => true
    ),
    catchError(
      err => {
        this.router.navigate(['/error'])
        return of(false);
      }
    ));
  }
}
