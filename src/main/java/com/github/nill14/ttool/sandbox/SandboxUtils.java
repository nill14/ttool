package com.github.nill14.ttool.sandbox;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;

public class SandboxUtils {

	
	public static ResourceBundle getMessageBundle() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String messageBundleName = facesContext.getApplication().getMessageBundle();
		Locale locale = facesContext.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);
		return bundle;
	}
	
	public static String getTranslation(String name, String key, Object... params) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, name);
		try {
			String msgFmt = bundle.getString(key);
			return MessageFormat.format(msgFmt, params);
		} catch (MissingResourceException e) {
			return String.format("???%s???", key);
		}
	}	
	
	public static String getELMessage(String elExpression, Object... args) {
		FacesContext context = FacesContext.getCurrentInstance();
		String value = context.getApplication().evaluateExpressionGet(context, elExpression, String.class);
		return MessageFormat.format(value, args);
	}
	
}
