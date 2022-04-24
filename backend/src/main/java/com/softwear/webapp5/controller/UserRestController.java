package com.softwear.webapp5.controller;

import java.net.URI;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.softwear.webapp5.data.UserChangePasswordDTO;
import com.softwear.webapp5.data.UserEditProfileDTO;
import com.softwear.webapp5.data.UserPageDTO;
import com.softwear.webapp5.data.UserRegisterDTO;
import com.softwear.webapp5.model.ShopUser;
import com.softwear.webapp5.service.TransactionService;
import com.softwear.webapp5.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{id}")
    public ResponseEntity<ShopUser> getUser(@PathVariable Long id) {

        Optional<ShopUser> user = userService.findById(id);

        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());

        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @GetMapping("/my")
    public ResponseEntity<ShopUser> getUser(HttpServletRequest request) {

        String username = request.getUserPrincipal().getName();

        if (username == null) {
            return ResponseEntity.notFound().build();

        } else {
            ShopUser user = userService.findByUsername(username).get();
            return ResponseEntity.ok(user);

        }

    }

    @GetMapping("")
	public ResponseEntity<UserPageDTO> getUsers(@RequestParam(required=false) Integer page) {

		Pageable pageable;

		if (page == null) {
			pageable = Pageable.unpaged();

		} else {
			pageable = PageRequest.of(page - 1, 10);

		}

		Page<ShopUser> user;

		user = userService.findAll(pageable);
		int totalPages = user.getTotalPages();

		UserPageDTO userPageDTO = new UserPageDTO(user.toList(), totalPages);

		return ResponseEntity.ok(userPageDTO);
	}

    @PostMapping("")
	public ResponseEntity<ShopUser> createUser(@RequestBody UserRegisterDTO registerData) {

        ShopUser user = new ShopUser(registerData);
		user.setPassword(passwordEncoder.encode(registerData.getPassword()));
        
        userService.saveUser(user);

        transactionService.saveEmptyCart(user);
        transactionService.saveEmptyWishlist(user);

        URI location = fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(user);
	}

    @PutMapping("/my")
    public ResponseEntity<ShopUser> updateOwnUser(HttpServletRequest request,
            @RequestBody UserEditProfileDTO EditData) {

        String username = request.getUserPrincipal().getName();

        if (username == null) {
            return ResponseEntity.notFound().build();

        } else {
            ShopUser user = userService.findByUsername(username).get();

            userService.updateProfile(user, EditData);
            userService.saveUser(user);

            return ResponseEntity.ok(user);
        }
    }

    @PutMapping("/my/password")
    public ResponseEntity<ShopUser> updateOwnUserPassword(HttpServletRequest request, @RequestBody UserChangePasswordDTO passwords) {

        String username = request.getUserPrincipal().getName();

        if (username == null) {
            return ResponseEntity.notFound().build();

        }
        ShopUser user = userService.findByUsername(username).get();

        if(passwordEncoder.matches(passwords.getOldPassword(), user.getPassword())) {

            String password = passwords.getNewPassword();

            userService.updatePass(user, passwordEncoder.encode(password));
            return ResponseEntity.ok(user);

        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
	public ResponseEntity<ShopUser> updateUser(@PathVariable Long id, @RequestBody ShopUser updatedUser) {

        Optional<ShopUser> userOptional = userService.findById(id);

        if (userOptional.isPresent()) {

            ShopUser user = userOptional.get();

            userService.updateUser(user, updatedUser);
            userService.saveUser(user);

            return ResponseEntity.ok(user);

        }

        return ResponseEntity.notFound().build();
	}

    @DeleteMapping("/my")
	public ResponseEntity<ShopUser> deleteOwnUser(HttpServletRequest request) {

		String username = request.getUserPrincipal().getName();

		if(username == null) {
            return ResponseEntity.notFound().build();

		}		
        ShopUser user = userService.findByUsername(username).get();

        userService.delete(user.getId());
		return ResponseEntity.ok(user);		
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<ShopUser> deleteUser(@PathVariable Long id) {

		Optional<ShopUser> user = userService.findById(id);

		if(user.isPresent()) {
			userService.delete(id);
			return ResponseEntity.ok(user.get());

		}		
		return ResponseEntity.notFound().build();
	}
    
}
