package com.softwear.webapp5.controller;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softwear.webapp5.security.jwt.AuthResponse;
import com.softwear.webapp5.security.jwt.AuthResponse.Status;
import com.softwear.webapp5.security.jwt.LoginRequest;
import com.softwear.webapp5.security.jwt.UserLoginService;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

	
	@Autowired
	private UserLoginService userService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@CookieValue(name = "AccessToken", required = false) String accessToken,
			@CookieValue(name = "RefreshToken", required = false) String refreshToken,
			@RequestBody LoginRequest loginRequest) {
		
		return userService.login(loginRequest, accessToken, refreshToken);
	}

	@PostMapping("/refresh")
	public ResponseEntity<AuthResponse> refreshToken(
			@CookieValue(name = "RefreshToken", required = false) String refreshToken) {

		return userService.refresh(refreshToken);
	}

	@PostMapping("/logout")
	public ResponseEntity<AuthResponse> logOut(HttpServletRequest request, HttpServletResponse response) {
		return ResponseEntity.ok(new AuthResponse(Status.SUCCESS, userService.logout(request, response)));
	}

}
