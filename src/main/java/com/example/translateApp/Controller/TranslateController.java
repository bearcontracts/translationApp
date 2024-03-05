package com.example.translateApp.Controller;

import com.example.translateApp.Service.ExternalTranslationService;
import com.example.translateApp.Service.TranslationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class TranslateController {

    @Autowired
    private TranslationService translationService;

    @Autowired(required = false)
    private ExternalTranslationService externalTranslationService;

    @GetMapping(value = "/hello-rest")
    @ResponseBody
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping(value = "/hello")
    public ModelAndView helloHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @GetMapping(value = "/translateHello")
    @ResponseBody
    public String translateHello(@RequestParam("language") String language) {
        log.info("Translation to: " + language);
        return translationService.translateHelloWorld(language);
    }

    @PostMapping(value = "/translator")
    public ResponseEntity<String> translateText(
            @RequestParam("source") String sourceLanguage,
            @RequestParam("target") String targetLanguage,
            @RequestParam("text") String text) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            log.info("User in authenticated");
            try {
                String translation;
                if (externalTranslationService != null) {
                    log.info("External API is connected");
                    translation = externalTranslationService.translate(sourceLanguage, targetLanguage, text);
                } else {
                    log.info("Fetching data from DB");
                    translation = translationService.translate(sourceLanguage, targetLanguage, text);
                }
                log.info("Translation successful");
                return ResponseEntity.ok(translation);
            } catch (Exception e) {
                log.error("Translation failed");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Translation failed!");
            }
        } else {
            log.warn("Not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User is not authenticated");
        }
    }


}
