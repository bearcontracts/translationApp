package com.example.translateApp.Service;

import com.example.translateApp.Model.Translation;
import com.example.translateApp.Repository.TranslationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TranslationService {

    @Autowired
    private TranslationRepository translationRepository;

    public String translateHelloWorld(String language) {
        Optional<Translation> optional = translationRepository.findFirstByLanguageCode(language);
        if (optional.isPresent()) {
            Translation translation = optional.get();
            return translation.getTranslation();
        } else {
            return "Translation not found for desired language: " + language;
        }
    }

    public String translate(String sourceLanguage, String targetLanguage, String text) throws InterruptedException {
        Optional<Translation> translationSource = translationRepository.findFirstByLanguageCode(sourceLanguage);
        Optional<Translation> translationTarget = translationRepository.findFirstByLanguageCode(targetLanguage);
        if (translationSource.isPresent() && translationTarget.isPresent()) {
            Translation t1 = translationSource.get();
            Translation t2 = translationTarget.get();

            return text.replace(t1.getTranslation(), t2.getTranslation());

        }
        return "Translation data not found";
    }

}
