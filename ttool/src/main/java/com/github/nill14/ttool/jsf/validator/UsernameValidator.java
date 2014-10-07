package com.github.nill14.ttool.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.github.nill14.ttool.service.IUserService;

@Component
@Scope("request")
@FacesValidator("usernameValidator")
public class UsernameValidator implements Validator {

	@Inject
	private IUserService userService;

	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {

		String username = value.toString();

		if (userService.isUsernameDuplicated(username)) {
			String text = context.getApplication()
					.evaluateExpressionGet(context, "#{msg.DUPLICATE_USERNAME}", String.class);
			FacesMessage facesMsg = new FacesMessage(text);
			facesMsg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(facesMsg);
		}

	}

}