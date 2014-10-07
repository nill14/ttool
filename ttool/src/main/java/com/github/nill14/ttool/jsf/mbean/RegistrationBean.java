package com.github.nill14.ttool.jsf.mbean;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.github.nill14.ttool.service.IUserService;

//@ManagedBean
@Named("registrationBean")
@Scope("request")
public class RegistrationBean implements Serializable
{
	
	private static final Logger log = LoggerFactory.getLogger(RegistrationBean.class);
	private static final long serialVersionUID = -9040217024800473940L;

	private String username;
	
	@Size(min=5, max=25)
    private String password;
    
    @Email
    private String email;
    
    @Inject
    private transient IUserService service;
    
    public void validateUsername() {
    }
    

    public String register() {
    	
		service.createUser(username, password, email);
		log.debug("registration({}) succeeded", username);
		return "/welcome?faces-redirect=true";
    }



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






	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



}
