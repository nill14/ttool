package com.github.nill14.ttool.sandbox;

import java.io.Serializable;


public class ColumnModel implements Serializable {  

	private static final long serialVersionUID = 1L;

    private String property;  
    private String header;
    private boolean editable;

    public ColumnModel(String header, String property, boolean editable) {  
        this.header = header;  
        this.property = property;
		this.editable = editable;  
    }  

    
    public String getHeader() {  
        return header;  
    }  

    public String getProperty() {  
        return property;  
    }

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}  
} 