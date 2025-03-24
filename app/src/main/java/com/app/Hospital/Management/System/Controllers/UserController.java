package com.app.Hospital.Management.System.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Hospital.Management.System.entities.User;
import com.app.Hospital.Management.System.repositories.UserRepository;

@RestController
@RequestMapping("/api/register")
public class UserController {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;
	@PostMapping("/")
	public User register(@RequestBody User user)
	{ 
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepo.save(user);
		return user;
		
	}
	
	@GetMapping("/getmail")
	public User mail() {
		return userRepo.findByEmail("kalyan@example.com");
	}
}
