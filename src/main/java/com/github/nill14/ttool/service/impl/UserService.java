package com.github.nill14.ttool.service.impl;

import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.nill14.ttool.datarepo.UserRepository;
import com.github.nill14.ttool.entity.security.User;
import com.github.nill14.ttool.service.IUserService;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

@Service
public class UserService implements IUserService {

	@Inject
	private UserRepository repo; 
	
	@Override
	public Set<String> getUsernames() {
		return repo.findAllUsernames();
	}

	@Override
	public boolean validateLogin(String username, String password) {
		User user = repo.findByUsername(username);
		if (user != null) {
			return hash(password).equals(user.getPasswd());
		}
		return false;
	}

	@Override
	public String hash(String clearText) {
		String salt = "saltandbutterwithbread";
		return Hashing.sha1().hashString( salt + clearText + salt, Charsets.UTF_8 ).toString();
//		return DigestUtils.sha1Hex(salt + clearText + salt); commons-codec
	}
	


	@Override
	public User createUser(String username, String password, String email) {
		User user = repo.findByUsername(username);
		if (user != null) {
			throw new RuntimeException(String.format("User %s already exists!", username));
		}
		
		user = new User();
		user.setUsername(username);
		user.setPasswd(hash(password));
		user.setEmail(email);
		
		return repo.save(user);
	}

}
