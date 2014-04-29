package com.github.nill14.ttool.jsf.mbean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.github.nill14.ttool.sandbox.SandboxUtils;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Maps;

@Named("localeBean")
@Scope("session")
public class LocaleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(LocaleBean.class);

	private ImmutableList<Locale> locales;
	private Map<String, Locale> index;
	private List<String> languages; 
	private Locale locale;

    @PostConstruct
    public void init() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = facesContext.getViewRoot();
        Application application = facesContext.getApplication();
		locales = ImmutableList.copyOf(application.getSupportedLocales());
		
		log.debug("Installed locales: {}", locales);
		
		index = Maps.uniqueIndex(locales, new Function<Locale, String>() {
			@Override
			public String apply(Locale input) {
				return input.getDisplayLanguage(input);
			}
		});
		
		languages = ImmutableList.copyOf(
			ImmutableSortedSet.copyOf(index.keySet())
		);

		locale = viewRoot.getLocale();
    }
	

	public List<String> getLanguages() {
		return languages;
	}
	
	public String getDisplayLanguage() {
		return locale.getDisplayLanguage(locale);
	}

	public void setDisplayLanguage(String displayLanguage) {
		log.debug("New language: {}", displayLanguage);

		if (index.containsKey(displayLanguage)) {
			Locale newLocale = index.get(displayLanguage);
			switchLocale(newLocale);
		}
	}
	
	public String getLanguageCode() {
		return locale.getLanguage(); //lang code
	}

	// value change event listener
	public void languageChanged(ValueChangeEvent e) {
		log.debug("languageChanged({} -> {})", e.getOldValue(), e.getNewValue());
		if (e.getNewValue() instanceof String) {
			String newLanguage = e.getNewValue().toString();
			setDisplayLanguage(newLanguage);
		}
	}
	
	private void switchLocale(Locale newLocale) {
		if (this.locale.equals(newLocale)) return;
		log.info("Switching locale from {} to {}", this.locale, newLocale);
		this.locale = newLocale;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(newLocale);
	}
	
	public MenuModel getLanguageMenu() {
		DefaultMenuModel model = new DefaultMenuModel();

		for (String lang : getLanguages()) {
			DefaultMenuItem item = new DefaultMenuItem(lang);
			CommandButton btn = new CommandButton();
//			MenuButton
//			item.seta
			
			MethodExpression me = SandboxUtils.createMethodExpression("#{localeBean.language}");
			btn.setActionExpression(me);
			btn.addActionListener(new MethodExpressionActionListener(me));
			btn.setAjax(false);
			
//			if ( lang.equals(getDisplayLanguage()) ) {
//				item.setIcon("ui-icon-check");
//			} else {
//				item.setIcon("ui-icon-blank");
//			}
			
//			item.ad
//			item.getChildren().add(btn);
			model.addElement(item);
		}

		
		return model;
	}

}