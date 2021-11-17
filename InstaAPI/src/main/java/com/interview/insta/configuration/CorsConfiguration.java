package com.interview.insta.configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class CorsConfiguration {
    
    @Bean
    public WebMvcConfigurer corsConfigurer(){

        return new WebMvcConfigurer(){

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
            }

        };
    }
}
