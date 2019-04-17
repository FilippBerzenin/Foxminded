package com.berzenin.university.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.berzenin.university.dao.UserRepository;
import com.berzenin.university.model.security.User;

@Controller
public class RegistrationViewController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/registration")
	public String registration () {
		return "registration";
	}
	
	@PostMapping ("/registration")
	public String addNewUser (User user, Map<String, Object> model) {
		User userFromDb = userRepository.findByUsername(user.getUsername());
		if (userFromDb != null) {
			model.put("massage", "Users exists");
			return "registration";
		}
		user.setActive(true);
//		user.setRoles(Collections.singleton(Role.STUDENT));
		userRepository.save(user);
		return "redirect:/login";		
	}
}
