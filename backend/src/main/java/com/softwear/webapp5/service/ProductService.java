package com.softwear.webapp5.service;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public void save(Product product){
        repository.save(product);
    }

    public Optional<Product> findById(Long id){
        return repository.findById(id);
    }

    //  findbyprice, findbybrand

    public List<Product> findByName(String name){
        return repository.findByName(name);
    }


    public void deleteProduct(Long id) {
		Optional<Product> product = repository.findById(id);
		if(product.isPresent()) {
			repository.delete(product.get());
		}
	}


}