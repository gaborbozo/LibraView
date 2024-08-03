package hu.bozgab.movie.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "movie.tmdb")
@Configuration
public class TMDBProperties {

    private String url;

}
