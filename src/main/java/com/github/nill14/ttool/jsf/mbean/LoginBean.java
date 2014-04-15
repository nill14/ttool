package com.github.nill14.ttool.jsf.mbean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private IUserService service;
    
    @PostConstruct
    public void init() {
    	log.info("service: {}", service);
//    	if (service.getClass() == UserService.class) throw new RuntimeException("Must be proxy");
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

    public String login() throws ServletException, IOException {
    	log.info("loginBean#login");
    	
    	
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = ctx.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
            HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward(request, response);
            ctx.responseComplete();
        } catch (ServletException ex) {
            log.error("loginBean#login", ex);
        } catch (IOException ex) {
        	log.error("loginBean#login", ex);
        }    	
    	
        return "/welcome?faces-redirect=true";
    }


}
