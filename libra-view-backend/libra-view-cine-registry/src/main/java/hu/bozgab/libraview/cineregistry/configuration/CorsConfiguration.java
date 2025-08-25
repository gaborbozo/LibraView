package hu.bozgab.libraview.cineregistry.configuration;

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
                .allowedMethods(HttpMethod.GET.name())
                .maxAge(3600);
    }

}