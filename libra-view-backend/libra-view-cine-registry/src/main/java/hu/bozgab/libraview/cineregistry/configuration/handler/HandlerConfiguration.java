package hu.bozgab.libraview.cineregistry.configuration.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;


@Configuration
public class HandlerConfiguration {

    @Autowired
    ServerCodecConfigurer serverCodecConfigurer;

    @Autowired
    RequestedContentTypeResolver requestedContentTypeResolver;

    @Bean
    CustomResponseBodyResultHandler customResponseBodyResultHandler() {
        return new CustomResponseBodyResultHandler(serverCodecConfigurer.getWriters(), requestedContentTypeResolver);
    }

}
