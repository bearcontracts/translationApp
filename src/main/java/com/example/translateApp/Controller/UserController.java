package com.example.translateApp.Controller;

import com.example.translateApp.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping(value = "/secure")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/hello")
    public ResponseEntity<String> loginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            log.info("User logged in: " + username);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully logged in " + username);
        } else {
            log.warn("Not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to login");
        }
    }

    @PostMapping(value = "/admin")
    public ResponseEntity<String> addLanguageMessage(
            @RequestParam("language") String language,
            @RequestParam("message") String message) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getAuthorities().stream()
                    .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"))) {
                log.info("User is allowed to add language");
                userService.addTranslation(language, message);
                return ResponseEntity.ok("Language-Message pair added successfully");
            } else {
                log.error("Not admin");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("You are not authorized to perform this action");
            }
        } else {
            log.warn("Not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User is not authenticated");
        }
    }
}
