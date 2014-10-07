package com.github.nill14.ttool.jsf.mbean;
import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
  
@Named("menuBean")
@Scope("session")
public class MapMenuBean {  
      
    public void save() {  
        addMessage("Data saved");  
    }  
      
    public void update() {  
        addMessage("Data updated");  
    }  
      
    public void delete() {  
        addMessage("Data deleted");  
    }  
      
    public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
} 