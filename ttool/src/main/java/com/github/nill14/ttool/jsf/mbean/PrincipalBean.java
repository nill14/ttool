package com.github.nill14.ttool.jsf.mbean;

import java.io.Serializable;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.github.nill14.ttool.sandbox.SandboxUtils;

@Named("principalBean")
@Scope("session")
public class PrincipalBean implements Serializable
{
	
	private static final Logger log = LoggerFactory.getLogger(PrincipalBean.class);
	private static final long serialVersionUID = -9040217024800473940L;

    
	public String getUsername() {
		User principal = getPrincipal();
		if (principal != null) {
			return principal.getUsername();
		}

		return null;	
	}

	
	private User getPrincipal() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() instanceof User) {
			return (User) authentication.getPrincipal();
		}
		return null;
	}
	
	public boolean isLoggedIn() {
		return getPrincipal() != null;
	}
	
	public String logout() {
    	log.info("principalBean#logout");
    	
    	return SandboxUtils.forwardTo("/logout");
	}

}
