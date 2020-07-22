package com.excilys.controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.DTORole;
import com.excilys.dto.DTOUser;
import com.excilys.service.AuthenticationService;


@Controller
public class UserController {

	private PasswordEncoder passwordEncoder;
	DTORole roledto;
	private AuthenticationService authservice;
	
	public UserController(AuthenticationService serviceUser, PasswordEncoder passwordEncoder) {
		this.authservice = serviceUser;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping(value = {"/login"})
	public String login() {
		return "login";
	}

	@GetMapping(value = "/register")
	public String register(Model model) {
		return "register";
	}

	@PostMapping(value = "/register")
	public String register(@RequestParam(required = false, value = "userNameDTO") String userNameDTO,
			@RequestParam(required = false, value = "passwordDTO") String passwordDTO,
			@RequestParam(required = false, value = "roleDTO") String roleDTO) {
		
		if(roleDTO.equals("USER")) {
			 roledto = new DTORole.Builder().setRoleName(roleDTO).setId("1").build();
		}
		if(roleDTO.equals("ADMIN")) {
			 roledto = new DTORole.Builder().setRoleName(roleDTO).setId("2").build();
		}
		DTOUser userdto = new DTOUser.Builder().setPassword(passwordDTO).setRole(roledto).setUserName(userNameDTO).build();
		userdto.setPassword(passwordEncoder.encode(userdto.getPassword()));
		authservice.addUser(userdto);
		return "login";
	}
}