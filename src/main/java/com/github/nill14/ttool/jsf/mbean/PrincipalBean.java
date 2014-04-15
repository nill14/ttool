package com.github.nill14.ttool.jsf.mbean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

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
    	
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = ctx.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
            HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logout");
            dispatcher.forward(request, response);
            ctx.responseComplete();
        } catch (ServletException ex) {
            log.error("principalBean#logout", ex);
        } catch (IOException ex) {
        	log.error("principalBean#logout", ex);
        }    	
    	
        return null;
	}

}
