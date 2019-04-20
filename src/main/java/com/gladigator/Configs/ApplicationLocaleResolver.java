package com.gladigator.Configs;

import java.util.HashSet;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.CookieLocaleResolver;

public class ApplicationLocaleResolver extends CookieLocaleResolver {
	
	private HashSet<Locale> managedLocales;
	
	public ApplicationLocaleResolver() {
		managedLocales = new HashSet<>();
		managedLocales.add(Locale.forLanguageTag("pl-PL"));
		managedLocales.add(Locale.forLanguageTag("en-GB"));
	}

	@Override
    public Locale resolveLocale(HttpServletRequest request) {
		Locale locale = super.resolveLocale(request);
		if (managedLocales.contains(locale)) {
			return locale;
		} else {
			return this.getDefaultLocale();
		}
    }
}
