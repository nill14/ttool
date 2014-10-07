package com.github.nill14.ttool.service;

import java.util.List;

import com.github.nill14.ttool.entity.security.User;

public interface IUserService {
	
	boolean isUsernameDuplicated(String username);

	User createUser(String username, String password, String email);

	List<User> getAllUsers();

	/**
	 * 
	 * Pages are zero indexed, thus providing 0 for page will return the first page.
	 * @param page zero-based page index.
	 * @param size the size of the page to be returned.
	 * @return a page of users
	 */
	List<User> getPage(int page, int size);

	int getUsersCount();
	
}
