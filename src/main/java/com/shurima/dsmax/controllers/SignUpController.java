package com.shurima.dsmax.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shurima.dsmax.dao.entity.User;
import com.shurima.dsmax.services.UserService;

@Controller
public class SignUpController {
	@Autowired
	private UserService userService;

	@GetMapping("/registration")
	public String getRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "registration";

	}

	@PostMapping("/registration")
	public String registerUser(Model model, @Valid User user, Errors errors) {
		if (!errors.hasErrors()) {
			userService.save(user);
			return "redirect:/login";
		}
		model.addAttribute("user", user);
		return "registration";

	}
}
