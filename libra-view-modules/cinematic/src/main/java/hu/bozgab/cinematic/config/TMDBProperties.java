package hu.bozgab.cinematic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@ConfigurationProperties(prefix = "cinematic.tmdb")
@Configuration
public class TMDBProperties {

    private String url;

}
