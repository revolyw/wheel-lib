package cn.willow.demo.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class CorsConfig {
    List<String> allowedOrigins = new ArrayList<String>(){{
        add("https://localhost.*");
        add("http://localhost.*");
    }};

    @Bean
    public CorsWebFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(
            new PathPatternParser());
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsWebFilter(source, new RegexCorsProcessor());
    }

    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedMethod("OPTIONS");

        corsConfiguration.setAllowedOrigins(allowedOrigins);
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3628800L);
        return corsConfiguration;
    }
}