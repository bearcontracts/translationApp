package com.example.translateApp.Repository;

import com.example.translateApp.Model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Integer> {

    Optional<Translation> findFirstByLanguageCode(String language);
}
