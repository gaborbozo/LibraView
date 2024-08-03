package hu.bozgab.movie.config;

import hu.bozgab.movie.client.MovieTMDBJsonPlaceholderService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@EntityScan("hu.bozgab.movie.domain")
@EnableJpaRepositories(basePackages = {"hu.bozgab.movie.repository"})
@ComponentScan(basePackages = {"hu.bozgab.movie.controller", "hu.bozgab.movie.service", "hu.bozgab.movie.mapper"})
@Configuration
public class MovieConfiguration {

    private TMDBProperties tmdbProperties;

    public MovieConfiguration(TMDBProperties tmdbProperties) {
        this.tmdbProperties = tmdbProperties;
    }

    @Bean
    MovieTMDBJsonPlaceholderService movieTMDBJsonPlaceholderService() {
        RestClient client = RestClient.builder()
                .baseUrl(tmdbProperties.getUrl())
                .defaultHeaders(
                        httpHeaders -> {
                            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                        }
                )
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(client))
                .build();
        return factory.createClient(MovieTMDBJsonPlaceholderService.class);
    }

}
