package com.softwear.webapp5.controller;

/*import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softwear.webapp5.model.Product;
import com.softwear.webapp5.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductRepository products;
	
	@PostConstruct
	public void init() {
		ArrayList<String> routes= new ArrayList<>();
		routes.add("../assets/productos/item1.webp");
		products.save(new Product("Softcoat", "Coat made in ESPAÑA",routes,49.99));
		//users.save(new User());
	}
	
	@GetMapping("/userProfile") //Falta id
	public String user(Model model) {
	    return "userProfile";
	}
	
	@GetMapping("/")
	public Collection<Product> getProducts(){
		return products.findAll();
	}
	
	@GetMapping("/{id}")
	public Product getUser(@PathVariable long id) {
		return products.findById(id).orElseThrow();
	}
	
	@PostMapping("/userProfile")
	public ResponseEntity<Product> createItem(@RequestBody Product product) {

		products.save(product);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(product.getId()).toUri();

		return ResponseEntity.created(location).body(product);
	}
	
	@PutMapping("/{id}")
	public Product replaceItem(@PathVariable long id, @RequestBody Product newItem) {

		products.findById(id).orElseThrow();

		newItem.setId(id);
		products.save(newItem);
			
		return newItem;
	}

	@DeleteMapping("/{id}")
	public Product deleteItem(@PathVariable long id) {

		Product product = products.findById(id).orElseThrow();

		products.deleteById(id);
		
		return product;
	}
	


}
*/