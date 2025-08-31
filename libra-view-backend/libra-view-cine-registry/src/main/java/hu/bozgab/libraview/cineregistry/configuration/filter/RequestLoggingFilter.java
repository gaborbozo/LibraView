package hu.bozgab.libraview.cineregistry.configuration.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Slf4j
@Configuration
public class RequestLoggingFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        var request = exchange.getRequest();
        log.info("""
                        
                        --- %s --->>>
                        %s?%s
                        """.formatted(
                        request.getMethod(),
                        request.getPath().value(),
                        request.getQueryParams().toString()
                )
        );
        return chain.filter(exchange);
    }

}
