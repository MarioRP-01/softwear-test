package com.softwear.webapp5.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.net.URI;
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

import com.softwear.webapp5.model.User;
import com.softwear.webapp5.repository.UserRepository;



@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository users;
	
	@PostConstruct
	public void init() {
		users.save(new User("ale.2019@alumnos.urjc.es", "Ale","Morueco", "1234", "Arroyo", 671642, null, null ,null));
		//users.save(new User());
	}
	
	@GetMapping("/userProfile") //Falta id
	public String user(Model model) {
	    return "userProfile";
	}
	
	@GetMapping("/")
	public Collection<User> getUsers(){
		return users.findAll();
	}
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable long id) {
		return users.findById(id).orElseThrow();
	}
	
	@PostMapping("/userProfile")
	public ResponseEntity<User> createItem(@RequestBody User user) {

		users.save(user);

		URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

		return ResponseEntity.created(location).body(user);
	}
	
	@PutMapping("/{id}")
	public User replaceItem(@PathVariable long id, @RequestBody User newItem) {

		users.findById(id).orElseThrow();

		newItem.setId(id);
		users.save(newItem);
			
		return newItem;
	}

	@DeleteMapping("/{id}")
	public User deleteItem(@PathVariable long id) {

		User item = users.findById(id).orElseThrow();

		users.deleteById(id);
		
		return item;
	}
	
}
