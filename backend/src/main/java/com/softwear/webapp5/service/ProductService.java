package com.softwear.webapp5.service;

import com.softwear.webapp5.data.Size;
import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void save(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			productRepository.delete(product.get());
		}
	}

	public File getFirstImg(Product product){
		return product.getImgs().get(0);
	}

	public ArrayList<File> getNonFirstImgs(Product product){
		ArrayList<File> copiedArrayList = (ArrayList<File>) product.getImgs().clone();
		copiedArrayList.remove(0);
		return copiedArrayList;
	}

	public Map<Size, Boolean> getAvailableSizes(Product product){
		String name = product.getName();
		List<Size> AvailableSizes = productRepository.FindSizeAvailableByName(name);
		Map<Size, Boolean> AvailableSizesStatus = new HashMap<>();

		for (Size size : Size.values()){
			if (AvailableSizes.contains(size))
				AvailableSizesStatus.put(size, true);
			else 
				AvailableSizesStatus.put(size, false);
		}
		return AvailableSizesStatus;
	}

	public void updateInfo(Product oldProduct, Product u) {
		oldProduct.setName(u.getName());
		oldProduct.setDescription(u.getDescription());
		oldProduct.setPrice(u.getPrice());
		oldProduct.setStock(u.getStock());
		oldProduct.setSize(u.getSize());
		oldProduct.setImgs(u.getImgs());

		save(oldProduct);
	}

}