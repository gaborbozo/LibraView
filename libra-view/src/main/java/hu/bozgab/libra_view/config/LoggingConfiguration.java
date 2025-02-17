package hu.bozgab.libra_view.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;


@Configuration
public class LoggingConfiguration {

    @Bean
    public AbstractRequestLoggingFilter requestLoggingFilter() {
        return new AbstractRequestLoggingFilter() {
            @Override
            protected void beforeRequest(HttpServletRequest request, String message) {
                logger.info(message);
            }

            @Override
            protected void afterRequest(HttpServletRequest request, String message) {
                logger.info(message);
            }
        };
    }

}
