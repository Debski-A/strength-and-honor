package com.gladigator.integrationTests.Services;

import com.gladigator.Services.TranslationServiceGoogle;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TranslationServiceGoogleTest {

    private TranslationServiceGoogle translator = new TranslationServiceGoogle();

    @Test
    public void translate() {
        //given
        String text = "witaj przystojny kolego";
        String fromLang = "pl";
        String toLang = "en";
        //when
        String result = translator.translate(text, fromLang, toLang);
        //then
        assertThat(result, equalTo("hello handsome buddy"));
    }
}