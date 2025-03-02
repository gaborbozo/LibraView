package hu.bozgab.cinematic.config;

import hu.bozgab.cinematic.client.TMDBJsonPlaceholderService;
import hu.bozgab.cinematic.exception.TMDBAPIKeyNotSetException;

import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;


@Slf4j
@RequiredArgsConstructor
@EntityScan("hu.bozgab.cinematic.domain")
@EnableJpaRepositories(basePackages = { "hu.bozgab.cinematic.repository" })
@ComponentScan(basePackages = { "hu.bozgab.cinematic.controller", "hu.bozgab.cinematic.service", "hu.bozgab.cinematic.mapper" })
@Configuration
public class CinematicConfiguration {

    private final ObjectFactory<HttpSession> httpSessionFactory;

    @Value("${tmdb.authentication.token}")
    private String tmdbToken;

    private final TMDBProperties tmdbProperties;

    /**
     * @return TMDBJsonPlaceholderService client which adds to URI api_key on each request building
     */
    @Bean
    TMDBJsonPlaceholderService cinematicTMDBJsonPlaceholderService() {
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            HttpRequest encodedRequest = new HttpRequestWrapper(request) {
                @NonNull
                @Override
                public HttpHeaders getHeaders() {
                    HttpHeaders headers = super.getHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    if(tmdbToken == null) {
                        throw new TMDBAPIKeyNotSetException();
                    }

                    headers.setBearerAuth(tmdbToken);

                    return headers;
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
