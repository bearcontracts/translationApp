package com.example.translateApp.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("external")
public class ExternalTranslationService extends TranslationService{

    @Value("${systran.api.url}")
    private String apiUrl;

    @Value("${systran.api.key}")
    private String apiKey;
    @Override
    public String translate(String sourceLanguage, String targetLanguage, String inputText) throws InterruptedException {
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        headers.set("Authorization", "Key " + apiKey);

        String url = apiUrl + "/translation/text/translate" +
                "?source=" + sourceLanguage +
                "&target=" + targetLanguage +
                "&key=" + apiKey +
                "&input=" + inputText;

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new InterruptedException("Translation failed: " + response.getStatusCode());
        }
    }
}
