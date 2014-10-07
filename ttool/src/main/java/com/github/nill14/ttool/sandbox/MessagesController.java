package com.github.nill14.ttool.sandbox;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public final class MessagesController {  
	
	private MessagesController() {
		// no instances
	}
	
    public static void addInfo(String summary) {  
    	addMessage(FacesMessage.SEVERITY_INFO, null, summary, null);
    }  
  
    public static void addWarn(String summary) {  
    	addMessage(FacesMessage.SEVERITY_WARN, null, summary, null);
    }  
  
    public static void addError(String summary) {
    	addMessage(FacesMessage.SEVERITY_ERROR, null, summary, null);
    }  
  
    public static void addFatal(String summary) {
    	addMessage(FacesMessage.SEVERITY_FATAL, null, summary, null);
    }  	
    
	
	
    public static void addInfo(String clientId, String summary) {  
    	addMessage(FacesMessage.SEVERITY_INFO, clientId, summary, null);
    }  
  
    public static void addWarn(String clientId, String summary) {  
    	addMessage(FacesMessage.SEVERITY_WARN, clientId, summary, null);
    }  
  
    public static void addError(String clientId, String summary) {
    	addMessage(FacesMessage.SEVERITY_ERROR, clientId, summary, null);
    }  
  
    public static void addFatal(String clientId, String summary) {
    	addMessage(FacesMessage.SEVERITY_FATAL, clientId, summary, null);
    }  	
	
	
  
    public static void addInfo(String clientId, String summary, String detail) {  
    	addMessage(FacesMessage.SEVERITY_INFO, clientId, summary, detail);
    }  
  
    public static void addWarn(String clientId, String summary, String detail) {  
    	addMessage(FacesMessage.SEVERITY_WARN, clientId, summary, detail);
    }  
  
    public static void addError(String clientId, String summary, String detail) {
    	addMessage(FacesMessage.SEVERITY_ERROR, clientId, summary, detail);
    }  
  
    public static void addFatal(String clientId, String summary, String detail) {
    	addMessage(FacesMessage.SEVERITY_FATAL, clientId, summary, detail);
    }  
    
    
    
    private static void addMessage(Severity severity, String clientId, String summary, String detail) {
    	FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(severity, summary, detail));  
    }
}