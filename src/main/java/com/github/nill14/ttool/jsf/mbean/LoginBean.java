package com.github.nill14.ttool.jsf.mbean;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.github.nill14.ttool.service.IUserService;

//@ManagedBean
@Named("loginBean")
@Scope("session")
public class LoginBean implements Serializable
{
	
	private static final Logger log = LoggerFactory.getLogger(LoginBean.class);
	private static final long serialVersionUID = -9040217024800473940L;

	private String username;
    private String password;
    
    @Inject
    private transient IUserService service;
    
    
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

    public String login() {
    	boolean success = service.validateLogin(username, password);
    	if (success) {
    		log.debug("login({}) succeeded", username);
    		return "/welcome?faces-redirect=true";
    	
    	} else {
    		log.warn("User login(username={}) failed", username);
    		return "/login";
    	}
    }


}
