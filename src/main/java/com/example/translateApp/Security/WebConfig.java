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
        httpSecurity.csrf((csrf) -> csrf.ignoringRequestMatchers("/hello-rest")
                        .ignoringRequestMatchers("/secure/hello")
                        .ignoringRequestMatchers("/hello")
                        .ignoringRequestMatchers("/translateHello")
                        .ignoringRequestMatchers("/secure/admin")
                        .ignoringRequestMatchers("/translator"))
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/secure/hello").authenticated()
                        .requestMatchers("/secure/admin").authenticated()
                        .requestMatchers("/translator").authenticated()
                        .requestMatchers("/hello-rest").permitAll()
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/translateHello").permitAll())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
