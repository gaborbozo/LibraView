package hu.bozgab.movie.config;

import hu.bozgab.movie.client.TMDBJsonPlaceholderService;
import hu.bozgab.movie.exception.TMDBAPIKeyNotSetException;

import java.net.URI;

import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.UriComponentsBuilder;

import static hu.bozgab.movie.config.MovieResources.TMDB_API_KEY;


@Slf4j
@RequiredArgsConstructor
@EntityScan("hu.bozgab.movie.domain")
@EnableJpaRepositories(basePackages = { "hu.bozgab.movie.repository" })
@ComponentScan(basePackages = { "hu.bozgab.movie.controller", "hu.bozgab.movie.service", "hu.bozgab.movie.mapper" })
@Configuration
public class MovieConfiguration {

    private final ObjectFactory<HttpSession> httpSessionFactory;

    private final TMDBProperties tmdbProperties;

    /**
     * @return TMDBJsonPlaceholderService client which adds to URI api_key on each request building
     */
    @Bean
    TMDBJsonPlaceholderService movieTMDBJsonPlaceholderService() {
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            HttpRequest encodedRequest = new HttpRequestWrapper(request) {
                @NonNull
                @Override
                public URI getURI() {
                    URI uri = super.getURI();
                    Object apiKey = httpSessionFactory.getObject().getAttribute(TMDB_API_KEY);
                    if(apiKey == null) {
                        throw new TMDBAPIKeyNotSetException();
                    }

                    String escapedQuery = uri.getRawQuery();
                    // escapedQuery is null when no query parameters are used
                    if(escapedQuery == null) {
                        escapedQuery = "";
                    }
                    escapedQuery += (escapedQuery.contains("?") ? "?api_key=" : "&api_key=") + apiKey;
                    return UriComponentsBuilder.fromUri(uri)
                            .replaceQuery(escapedQuery)
                            .build(true).toUri();
                }
            };
            return execution.execute(encodedRequest, body);
        };

        RestClient client = RestClient.builder()
                .baseUrl(tmdbProperties.getUrl())
                .defaultHeaders(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                .requestInterceptor(interceptor)
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(client))
                .build();

        return factory.createClient(TMDBJsonPlaceholderService.class);
    }

}
