package com.github.nill14.ttool.sandbox;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SandboxUtils {
	
	private static final Logger log = LoggerFactory.getLogger(SandboxUtils.class);

	public static ResourceBundle getMessageBundle() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String messageBundleName = facesContext.getApplication()
				.getMessageBundle();
		Locale locale = facesContext.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName,
				locale);
		return bundle;
	}

	public static String getTranslation(String name, String key,
			Object... params) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication()
				.getResourceBundle(facesContext, name);
		try {
			String msgFmt = bundle.getString(key);
			return MessageFormat.format(msgFmt, params);
		} catch (MissingResourceException e) {
			return String.format("???%s???", key);
		}
	}
	
	public static String forwardTo(String path) {
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ectx = ctx.getExternalContext();
            HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
            HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
            ctx.responseComplete();
        } catch (ServletException ex) {
            log.error(String.format("forwarding to %s failed", path), ex);
        } catch (IOException ex) {
        	log.error(String.format("forwarding to %s failed", path), ex);
        }    	
    	
        return null;
	}

	public static String getELMessage(String elExpression, Object... args) {
		FacesContext context = FacesContext.getCurrentInstance();
		String value = context.getApplication().evaluateExpressionGet(context,
				elExpression, String.class);
		return MessageFormat.format(value, args);
	}

	public static MethodExpression createMethodExpression(String action) {
		final Class<?>[] paramTypes = new Class<?>[0];

		MethodExpression methodExpression = getExpressionFactory()
				.createMethodExpression(getELContext(), action, null,
						paramTypes);

		return methodExpression;
	}

	public static ValueExpression createValueExpression(String binding, Class<String> clazz) {
		final ValueExpression ve = getExpressionFactory()
				.createValueExpression(getELContext(), binding, String.class);
		return ve;
	}

	public static ELContext getELContext() {
		return FacesContext.getCurrentInstance().getELContext();
	}

	public static ExpressionFactory getExpressionFactory() {
		return getApplication().getExpressionFactory();
	}

	public static Application getApplication() {
		return FacesContext.getCurrentInstance().getApplication();
	}

}
