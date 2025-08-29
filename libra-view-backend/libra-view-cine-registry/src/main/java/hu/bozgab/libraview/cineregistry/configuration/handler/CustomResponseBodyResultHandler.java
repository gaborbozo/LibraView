package hu.bozgab.libraview.cineregistry.configuration.handler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.web.reactive.HandlerResult;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


public class CustomResponseBodyResultHandler extends org.springframework.web.reactive.result.method.annotation.ResponseBodyResultHandler {

    public CustomResponseBodyResultHandler(List<HttpMessageWriter<?>> writers, RequestedContentTypeResolver resolver) {
        super(writers, resolver);
        setOrder(super.getOrder() - 1);
    }

    @Override
    public boolean supports(HandlerResult result) {
        return super.supports(result);
    }

    @Override
    public Mono<Void> handleResult(ServerWebExchange exchange, HandlerResult result) {
        Object returnValue = result.getReturnValue();

        if(returnValue instanceof Mono<?>) {
            return ((Mono<?>) returnValue)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NO_CONTENT)))
                    .flatMap(value -> super.handleResult(exchange, new HandlerResult(result.getHandler(), value, result.getReturnTypeSource(), result.getBindingContext())));
        }

        return super.handleResult(exchange, result);
    }

}
