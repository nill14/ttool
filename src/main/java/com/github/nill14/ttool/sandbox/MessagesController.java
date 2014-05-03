package com.github.nill14.ttool.sandbox;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessagesController {  
  
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