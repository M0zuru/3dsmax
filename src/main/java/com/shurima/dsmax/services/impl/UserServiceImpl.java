package com.shurima.dsmax.services.impl;

import com.shurima.dsmax.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shurima.dsmax.dao.entity.User;
import com.shurima.dsmax.dao.repository.UserRepository;
import com.shurima.dsmax.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public User save(User user, Boolean savePassword) {
		if (savePassword) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		if (user.getRole() == null) {
			user.setRole("USER");
		}
		user.setEnabled(true);
		return userRepository.save(user);
	}

	@Override
	public User getCurrentUser() {
		return getUser(authenticationService.getAuthentication().getName());
	}

	@Override
	public User getUser(String username) {
		return userRepository.getUserByUsername(username);
	}
}
