package com.github.nill14.ttool.spring.security;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;

public class MyUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		log.info("User details of {}", username);
		
		if (!"admin".equals(username)) throw new UsernameNotFoundException(username);
		

		String password = passwordEncoder.encode("admin");
		log.warn("username={}, password={}", username, password); //FIXME remove
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<String> roles = ImmutableList.of("ROLE_USER");
		List<GrantedAuthority> authorities = toAuthorities(roles);
		
		User userDetails = new User(username, password, enabled, 
				accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		
		return userDetails;
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

}
