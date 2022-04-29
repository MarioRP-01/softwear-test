import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShopUser, Wishlist } from '@app/shared/classes';
import { Product } from '@app/shared/model';
import { ProductAvailabilityBySize } from '@app/shared/model/product-availability-by-size';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {

  productSizes!: Product[];
  availablesSizes!: ProductAvailabilityBySize[];
  user!: ShopUser | null;
  wishlist!: Wishlist | null;

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.productSizes = this.route.snapshot.data['productSizes'];
    this.availablesSizes = this.route.snapshot.data['availablesSizes'];
    this.user = this.route.snapshot.data['user'];
    this.wishlist = this.route.snapshot.data['wishlist']
  }

}
