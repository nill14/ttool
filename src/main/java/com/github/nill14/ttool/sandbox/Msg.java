package com.github.nill14.ttool.sandbox;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum Msg {
	
	//zero arg
	LANG,
	DUPLICATE_USERNAME;
	
	public String getMessage(ResourceBundle bundle, Object... args) {
		if (bundle == null) {
			throw new IllegalArgumentException("Bundle is null");
		}
		
		String string = bundle.getString(name());
		return MessageFormat.format(string, args);
	}
	
	public String getEL() {
		return String.format("msg.%s", name());
	}

}
