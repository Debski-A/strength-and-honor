package com.gladigator.Entities;

import java.util.List;
/**
 * Opakowuje encje, ktore beda podlegac translacji. Interfejs deklaruje metody, ktore beda odpowiedzialne
 * za ustawienie odpowiedniej translacji
 */
public interface Translationable<T extends Translation> {
	
	public List<T> getTranslations();
	
	public void setDescription(String description);

}
