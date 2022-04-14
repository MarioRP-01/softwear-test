package com.softwear.webapp5.data;

import java.util.EnumSet;

import javax.annotation.PostConstruct;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

public enum ProductFilter {
    OneByName {
        public Page<Product> doOperation(Pageable page) {

            return this.getProductService().findAllNames(page);
        }
    } ;

    public static ProductFilter stringToProductFilter(String filter) {

        return switch (filter) {
            case "OneByName" -> OneByName;
            default -> null;
        };
    }

    private ProductService productService;

    @Component
    public static class ProductFilterServiceInjector {

        @Autowired
        private ProductService productService;

        @PostConstruct
        public void postConstruct() {

            for (ProductFilter productFilter : EnumSet.allOf(ProductFilter.class)) {
                productFilter.setProductService(productService);
            }
        }
    }

    public abstract Page<Product> doOperation(Pageable page);

    private void setProductService(ProductService productService) {
        this.productService = productService;
    }

    protected ProductService getProductService() {
        return this.productService;
    }
   
}
