package hu.bozgab.libraviewtmdb.tmdb;

import java.util.List;

import hu.bozgab.libraviewtmdb.tmdb.exception.TMDBAPIKeyNotSetException;
import hu.bozgab.tmdb.generated.client.ApiClient;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestTemplate;


@Configuration
public class TMDBConfiguration {

    @Value("${app.tmdb.authentication.token}")
    private String token;

    @Bean
    ApiClient apiClientProvider() {
        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
            HttpRequest encodedRequest = new HttpRequestWrapper(request) {
                @NonNull
                @Override
                public HttpHeaders getHeaders() {
                    HttpHeaders headers = super.getHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);

                    if(token == null) {
                        throw new TMDBAPIKeyNotSetException();
                    }
                    headers.setBearerAuth(token);

                    return headers;
                }
            };
            return execution.execute(encodedRequest, body);
        };
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(List.of(interceptor));

        return new ApiClient(restTemplate);
    }

}
