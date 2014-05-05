package com.github.nill14.ttool.jsf.mbean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.web.WebAttributes;

import com.github.nill14.ttool.sandbox.MessagesController;
import com.github.nill14.ttool.sandbox.SandboxUtils;
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
    private boolean persistentLogin;
    
    @Inject
    private IUserService service;
    
    @PostConstruct
    public void init() {
    	log.info("service: {}", service);
//    	if (service.getClass() == UserService.class) throw new RuntimeException("Must be proxy");
    	
    	
//    	<h:outputText
//        rendered="#{param.loginFailed == 1 and SPRING_SECURITY_LAST_EXCEPTION != null}">
//        <span class="msg-error">#{SPRING_SECURITY_LAST_EXCEPTION.message}</span>
//    </h:outputText>
    }
    
    public void updateMessages(boolean update) {
        log.debug("Start LoginBean.updateMessages");
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
		Exception ex = (Exception) ctx.getSessionMap().get(WebAttributes.AUTHENTICATION_EXCEPTION);

	    if (ex != null) {
	    	String message = ex.getMessage();
			log.error("Authentication Failed! {}", message);
	        log.error("Authentication Failed! ", ex);
	        MessagesController.addError("loginMessages", message);
	    }
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
    	
    	return SandboxUtils.forwardTo("/j_spring_security_check");
    }

	public boolean isPersistentLogin() {
		return persistentLogin;
	}

	public void setPersistentLogin(boolean persistentLogin) {
		this.persistentLogin = persistentLogin;
	}


}
