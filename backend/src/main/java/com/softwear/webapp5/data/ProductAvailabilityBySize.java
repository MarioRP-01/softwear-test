package com.softwear.webapp5.data;

import java.util.List;

public class ProductAvailabilityBySize {
    List<Boolean> availableSize;
    List<ProductSize> size;

    public ProductAvailabilityBySize(List<Boolean> availableSize, List<ProductSize> size) {
        this.availableSize = availableSize;
        this.size = size;
    }
}