package com.github.nill14.ttool.jsf.mbean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
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
	
	private Locale locale;

    @PostConstruct
    public void init() {
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        updateLanguages();
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
	
	public String getLanguage() {
		return locale.getLanguage(); //lang code
	}

	// value change event listener
	public void languageChanged(ValueChangeEvent e) {
		String newLanguage = e.getNewValue().toString();
		setDisplayLanguage(newLanguage);
	}
	
	private void switchLocale(Locale newLocale) {
		if (this.locale.equals(newLocale)) return;
		log.info("Switching locale from {} to {}", this.locale, newLocale);
		this.locale = newLocale;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(newLocale);
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