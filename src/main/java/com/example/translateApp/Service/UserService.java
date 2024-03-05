package com.example.translateApp.Service;

import com.example.translateApp.Model.Translation;
import com.example.translateApp.Repository.TranslationRepository;
import com.example.translateApp.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TranslationRepository translationRepository;

    public void addTranslation(String language, String message) {
        Translation translation = new Translation();
        translation.setLanguageCode(language);
        translation.setTranslation(message);
        translationRepository.save(translation);
    }
}
