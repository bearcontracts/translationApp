package com.example.translateApp.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) -> requests.requestMatchers("/secure/hello").authenticated()
                        .requestMatchers("/secure/admin").authenticated()
                        .requestMatchers("/translator").authenticated()
                        .requestMatchers("/hello-rest").permitAll()
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/translateHello").permitAll())
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/secure/hello")
                        .ignoringRequestMatchers("/secure/admin")
                        .ignoringRequestMatchers("/translator"))
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
