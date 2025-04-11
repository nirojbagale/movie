package niroj.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow your specific frontend domains
        config.addAllowedOrigin("http://localhost:3000"); // Local development
        config.addAllowedOrigin("https://digital-movie-application.onrender.com"); // Production frontend

        // Allow all methods (GET, POST, etc.)
        config.addAllowedMethod("*");

        // Allow all headers (or specific ones if needed)
        config.addAllowedHeader("*");

        // Allow credentials (cookies, etc.)
        config.setAllowCredentials(true);

        // Register the configuration
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
