package hu.bozgab.libraview.master.configuration.security;

import java.util.Arrays;

import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;


@EnableWebFlux
@Configuration
public class CorsConfiguration implements WebFluxConfigurer {

    @Value("${app.client.http.address}")
    private String clientHttpAddress;

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins(clientHttpAddress)
                .allowedMethods(Arrays.stream(HttpMethod.values()).map(HttpMethod::name).toArray(String[]::new))
                .allowedHeaders("*")
                .exposedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

}