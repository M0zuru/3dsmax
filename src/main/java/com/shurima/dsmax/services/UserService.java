package com.shurima.dsmax.services;

import com.shurima.dsmax.dao.entity.User;

public interface UserService {
	User save(User user, Boolean savePassword);
	User getCurrentUser();
	User getUser(String username);
}
