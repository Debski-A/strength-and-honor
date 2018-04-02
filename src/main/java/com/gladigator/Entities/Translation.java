package com.gladigator.Entities;

/**
 * Opakowuje encje odpowiedzialne za translacje. Deklaruje metody odpowiedzialne za ustalenie jezyka, opcji domyslnej 
 * oraz pozyskanie tlumaczonego wyrazu
 */
public interface Translation {

	public String getLanguage();

	public Boolean getIsDefault();

	public String getTranslatedDescription();
}
