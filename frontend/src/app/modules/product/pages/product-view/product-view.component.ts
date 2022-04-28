import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShopUser, Transaction } from '@app/shared/classes';
import { Product } from '@app/shared/model';
import { ProductAvailabilityBySize } from '@app/shared/model/product-availability-by-size';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {

  product!: Product;
  availablesSizes!: ProductAvailabilityBySize[];
  user!: ShopUser | null;
  wishlist!: Transaction | null;

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.product = this.route.snapshot.data['product'];
    this.availablesSizes = this.route.snapshot.data['availablesSizes'];
    this.user = this.route.snapshot.data['user'];
    this.wishlist = this.route.snapshot.data['wishlist']
  }

}
