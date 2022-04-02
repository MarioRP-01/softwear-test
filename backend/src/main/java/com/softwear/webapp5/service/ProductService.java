package com.softwear.webapp5.service;

import com.softwear.webapp5.data.ProductAvailabilityBySize;
import com.softwear.webapp5.data.ProductSize;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.util.ArrayList;
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

	public Page<Product> findBySize(ProductSize size, Pageable pageable){
		return productRepository.findBySize(size, pageable);
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

	public String getFirstImage(Product product) {
		if(product.getImages().size() > 0) {
			return product.getImage(0);
		}
		return "";
	}

	public Blob getFirstImageFile(Product product){
		if(product.getImageFiles().size() > 0){
			return product.getImageFile(0);
		}
		return null;
	}

	public List<String> getNonFirstImages(Product product) {
		List<String> listCopy = new ArrayList<>(product.getImages());
		if(listCopy.size() > 0) {
			listCopy.remove(0);
		}
		return listCopy;
	}

	public ArrayList<Blob> getNonFirstImageFiles(Product product){
		ArrayList<Blob> copiedArrayList = new ArrayList<>(product.getImageFiles());
		if (copiedArrayList.size() > 0) {
			copiedArrayList.remove(0);
		}
		return copiedArrayList;
	}

	public List<ProductAvailabilityBySize> getAvailableSizesStatus(Product product){
		String name = product.getName();
		List<ProductSize> availableSizes = productRepository.FindSizeAvailableByName(name);
		List<ProductAvailabilityBySize> availableSizesStatus = new ArrayList<>();

		boolean isAvailableSize;
		for (ProductSize size : ProductSize.values()){
			isAvailableSize = availableSizes.contains(size);
			ProductAvailabilityBySize productAvailabilityBySize; 
			productAvailabilityBySize = new ProductAvailabilityBySize(isAvailableSize, size);

			availableSizesStatus.add(productAvailabilityBySize);
		}

		return availableSizesStatus;
	}

	public void addStock(Product product, int quantity) {
		if(quantity > 0){
			product.setStock(product.getStock() + quantity);
			save(product);
		}
	}

	public void updateInfo(Product oldProduct, Product u) {
		oldProduct.setName(u.getName());
		oldProduct.setDescription(u.getDescription());
		oldProduct.setPrice(u.getPrice());
		oldProduct.setStock(u.getStock());
		oldProduct.setSize(u.getSize());
		save(oldProduct);
	}

	public boolean checkStock(Product product, int quantity) {
		return quantity <= product.getStock();
	}

	public void deleteStock(Product product, int quantity) {
		if(checkStock(product, quantity)) {
			product.setStock(product.getStock() - quantity);
			save(product);
		}
	}

	public Optional<Product> findByNameAndSize(String name, ProductSize size) {
		return productRepository.findByNameAndSize(name, size);
	}

	public List<Long> getLeastBoughtProducts(int num) {
        List<Long> leastBoughtProducts = productRepository.getLeastBoughtProducts(num);
        return leastBoughtProducts;
    }

	public Page<Product> findAllNames(Pageable page){
		return productRepository.findAllNames(page);
	}

//	public Optional<Product> findOneName(String name){
//        return productRepository.findOneName(name);
//    }
}