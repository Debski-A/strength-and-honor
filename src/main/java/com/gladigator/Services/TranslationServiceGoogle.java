package com.gladigator.Services;

import com.gladigator.Exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Service
public class TranslationServiceGoogle implements TranslationService {

    private static final Logger LOG = LoggerFactory.getLogger(TranslationServiceGoogle.class);

    private static final String API_URL = "https://script.google.com/macros/s/AKfycbyLc7Dh2LoT1vbCr5c7Fa-TKaDqEDnND7o_PGITHMUwEDFXJU4/exec";

    @Override
    public String translate(String text, String fromLang, String toLang) {
        String apiUrlWithParams = prepareUrl(text, fromLang, toLang);
        String result = connectWithApiAndObtainResult(apiUrlWithParams);
        return result;
    }

    private String prepareUrl(String text, String fromLang, String toLang) {
        try {
            text = URLEncoder.encode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String format = API_URL + "?q=%s&target=%s&source=%s";
        String apiUrlWithParams = String.format(format, text, toLang, fromLang);
        LOG.debug("Google Translate API url with params = {}", apiUrlWithParams);
        return apiUrlWithParams;
    }

    private String connectWithApiAndObtainResult(String apiUrlWithParams) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(apiUrlWithParams);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
        } catch (MalformedURLException ex) {
            LOG.error(ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            LOG.error(ex.getMessage());
            throw new ServiceException("Some connection issues with " + apiUrlWithParams + " url", ex);
        }
        return response.toString();
    }
}
