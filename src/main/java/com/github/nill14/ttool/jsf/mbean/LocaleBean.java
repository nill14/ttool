package com.github.nill14.ttool.jsf.mbean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Maps;

@Named("localeBean")
@Scope("session")
public class LocaleBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(LocaleBean.class);

	private String displayLanguage;

	private static ImmutableList<Locale> locales = ImmutableList.of(
			Locale.ENGLISH, 
			Locale.GERMAN,
			Locale.FRENCH,
			new Locale("cs"),
			new Locale("sk"),
			new Locale("ru")
	);
	
	private Map<String, Locale> index;
	
	private List<String> languages; 
	
	public LocaleBean() {
		updateLanguages();
	}

	public List<String> getLanguages() {
		return languages;
	}
	
	public String getDisplayLanguage() {
		if(displayLanguage == null) {
			final Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			displayLanguage = locale.getDisplayLanguage();
		}
		
		return displayLanguage;
	}

	public void setDisplayLanguage(String displayLanguage) {
		this.displayLanguage = displayLanguage;
	}

	// value change event listener
	public void languageChanged(ValueChangeEvent e) {

		String newLanguage = e.getNewValue().toString();
		log.debug("New language: {}", newLanguage);

		if (index.containsKey(newLanguage)) {
			Locale newLocale = index.get(newLanguage);
			this.displayLanguage = newLanguage;
			switchLocale(newLocale);
		}
		
	}
	
	private void switchLocale(Locale newLocale) {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		log.info("Switching locale from {} to {}", locale, newLocale);
		FacesContext.getCurrentInstance().getViewRoot().setLocale(newLocale);
		updateLanguages();
	}
	
	private final void updateLanguages() {
		index = Maps.uniqueIndex(locales, new Function<Locale, String>() {

			@Override
			public String apply(Locale input) {
				return input.getDisplayLanguage(input);
			}
		});
		
		languages = ImmutableList.copyOf(
			ImmutableSortedSet.copyOf(index.keySet())
		);
	}

}