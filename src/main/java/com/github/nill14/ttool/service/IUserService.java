package com.github.nill14.ttool.service;

import java.util.Set;

import com.github.nill14.ttool.entity.security.User;

public interface IUserService {
	
	Set<String> getUsernames();

	boolean validateLogin(String username, String password);
	
	String hash(String clearText);
	
	User createUser(String username, String password, String email);
	
}
