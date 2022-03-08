package com.softwear.webapp5.service;

import com.softwear.webapp5.data.ProductAvailabilityBySize;
import com.softwear.webapp5.data.ProductSize;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Page<Product> findByName(String name, Pageable pageable) {
		return productRepository.findByName(name, pageable);
	}

    public Page<Product> findByDescription(String description, Pageable pageable) {
		return productRepository.findByDescription(description, pageable);
	}

    public Page<Product> findByPrice(double price, Pageable pageable) {
		return productRepository.findByPrice(price, pageable);
	}

    public Page<Product> findByStock(Long stock, Pageable pageable) {
		return productRepository.findByStock(stock, pageable);
	}

    public Page<Product> findBySupplier(String supplier, Pageable pageable) {
		return productRepository.findBySupplier(supplier, pageable);
	}

    public Page<Product> findByBrand(String Brand, Pageable pageable) {
		return productRepository.findByBrand(Brand, pageable);
	}

    public Page<Product> findByManufactDate(String ManufactDate, Pageable pageable) {
		return productRepository.findByManufactDate(ManufactDate, pageable);
	}

    public void save(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			productRepository.delete(product.get());
		}
	}

	public String getFirstImg_rout(Product product){
		return product.getImg_routes().get(0);
	}

	public ArrayList<String> getNonFirstImg_routes(Product product){
		ArrayList<String> copiedArrayList = (ArrayList<String>) product.getImg_routes().clone();
		copiedArrayList.remove(0);
		return copiedArrayList;
	}

	public ProductAvailabilityBySize getAvailableSizesStatus(Product product){
		String name = product.getName();
		List<ProductSize> availableSizes = productRepository.FindSizeAvailableByName(name);
		List<Boolean> availableSizesStatus = new ArrayList<>();

		for (ProductSize size : ProductSize.values()){
			availableSizesStatus.add(availableSizes.contains(size));
		}

		ProductAvailabilityBySize productAvailabilityBySize = 
			new ProductAvailabilityBySize(availableSizesStatus, Arrays.asList(ProductSize.values()));
			
		return productAvailabilityBySize;
	}

}