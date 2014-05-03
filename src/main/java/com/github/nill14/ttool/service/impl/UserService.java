package com.github.nill14.ttool.service.impl;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.nill14.ttool.datarepo.UserRepository;
import com.github.nill14.ttool.entity.security.User;
import com.github.nill14.ttool.service.IUserService;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

@Service
public class UserService implements IUserService, UserDetailsService {

	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Inject
	private UserRepository repo; 
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean isUsernameDuplicated(String username) {
		return repo.findByUsername(username) != null;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("User details of {}", username);
		
		User user = repo.findByUsername(username);
		if (user == null) {
			if (repo.count() == 0 && "admin".equals(username)) {
				return adminUser();
			} else {
				throw new UsernameNotFoundException(username);
			}
		}
		
//		log.warn("expected admin password={}", passwordEncoder.encode("admin"));
		
		String password = user.getPasswd();
		boolean enabled = user.isActivated();
		boolean accountNonExpired = !user.isDisabled();
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		List<String> roles = ImmutableList.of("ROLE_USER");
		List<GrantedAuthority> authorities = toAuthorities(roles);
		
		return new org.springframework.security.core.userdetails.User(username, password, enabled, 
				accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	private UserDetails adminUser() {
		String username = "admin";
		String password = passwordEncoder.encode("admin");
		//$2a$10$LDOX7QLqiyPcIKwdsc1MWuIkohpk37xSDgGJkTc9tmiLPBW5Ih3WC
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<String> roles = ImmutableList.of("ROLE_USER");
		List<GrantedAuthority> authorities = toAuthorities(roles);
		
		return new org.springframework.security.core.userdetails.User(username, password, enabled, 
				accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	private List<GrantedAuthority> toAuthorities(List<String> roles) {
		return FluentIterable.from(roles).transform(
				new Function<String, GrantedAuthority>() {

					@Override
					public GrantedAuthority apply(String role) {
						return new SimpleGrantedAuthority(role);
					}
		}).toList();
	}	

	@Override
	public User createUser(String username, String password, String email) {
		User user = repo.findByUsername(username);
		if (user != null) {
			throw new RuntimeException(String.format("User %s already exists!", username));
		}
		
		user = new User();
		user.setUsername(username);
		user.setPasswd(passwordEncoder.encode(password));
		user.setEmail(email);
		user.setDisabled(false);
		user.setActivated(false);
		
		return repo.save(user);
	}
	
	@Override
	@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
	public List<User> getAllUsers() {
		return ImmutableList.copyOf(repo.findAll());
	}	
	
	@Override
	@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
	public List<User> getPage(int page, int size) {
		Pageable pageable = new PageRequest(page, size);
		
		return ImmutableList.copyOf(repo.findAll(pageable));
	}
	
	@Override
	public int getUsersCount() {
		return (int) repo.count();
	}

}
