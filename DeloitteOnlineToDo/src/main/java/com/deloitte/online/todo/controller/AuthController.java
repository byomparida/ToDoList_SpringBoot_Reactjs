package com.deloitte.online.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deloitte.online.todo.entity.UserEntity;
import com.deloitte.online.todo.repository.UserRepository;
import com.deloitte.online.todo.request.LoginRequest;
import com.deloitte.online.todo.request.SignupRequest;
import com.deloitte.online.todo.response.JwtResponse;
import com.deloitte.online.todo.response.MessageResponse;
import com.deloitte.online.todo.security.jwt.JwtUtils;
import com.deloitte.online.todo.service.UserDetailsImpl;
import com.deloitte.online.todo.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/{version:v[1-9]|v[0-9]{2,}}/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@ModelAttribute LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@ModelAttribute SignupRequest signUpRequest) {
		if (userRepository.existsByUserName(signUpRequest.getUserName())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		// Create new user's account
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(signUpRequest.getUserName());
		userEntity.setPassword(encoder.encode(signUpRequest.getPassword()));
		userRepository.save(userEntity);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	

	@GetMapping("/userDetails/{userName}")
	public UserDetails getUserDetailsByName(@PathVariable(name = "version", required = true) String version,
			@ModelAttribute String userName) {
		return userDetailsServiceImpl.loadUserByUsername(userName);
	}
}
