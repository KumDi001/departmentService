package com.local.departmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.local.departmentservice.entity.AuthRequest;
import com.local.departmentservice.entity.AuthResponse;
import com.local.departmentservice.entity.Department;
import com.local.departmentservice.exceptions.DepartmentAlreadyExistsException;
import com.local.departmentservice.exceptions.DepartmentNotFoundException;
import com.local.departmentservice.service.DepartmentService;
import com.local.departmentservice.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class JwtAuthController {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	DepartmentService departmentService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome this endpoint is validated with Jwt Token";
	}

	@PostMapping("/generateTokenV1")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		if (authentication.isAuthenticated()) {
			String token = jwtUtil.generateToken(userDetails);
			AuthResponse response = AuthResponse.builder().username(userDetails.getUsername()).token(token).build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new UsernameNotFoundException("Invalid user request!");
		}
	}

	@PostMapping("/addNewDepartment")
	public Department addNewUser(@RequestBody Department userInfo) throws DepartmentAlreadyExistsException, DepartmentNotFoundException {
		return departmentService.saveDepartment(userInfo);
	}

	// Removed the role checks here as they are already managed in SecurityConfig

	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		if (authentication.isAuthenticated()) {
			return jwtUtil.generateToken(userDetails);
		} else {
			throw new UsernameNotFoundException("Invalid user request!");
		}
	}

}
