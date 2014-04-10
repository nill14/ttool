package com.github.nill14.ttool.service;

import com.github.nill14.ttool.entity.security.User;

public interface IUserService {
	
	boolean isUsernameDuplicated(String username);

	boolean validateLogin(String username, String password);
	
	String hash(String clearText);
	
	User createUser(String username, String password, String email);
	
}
