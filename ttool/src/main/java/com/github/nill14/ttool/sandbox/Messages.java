package com.github.nill14.ttool.sandbox;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum Messages {

	ABC, // 1 arg
	CDE, // 1 arg
	EFG;

	private static final ResourceBundle rb = ResourceBundle
			.getBundle(Messages.class.getPackage().getName() + ".MessageBundle");

	public String toString() {
		return format();
	}

	public String format(Object... args) {
		return MessageFormat.format(rb.getString(name()), args);
	}
	
}
