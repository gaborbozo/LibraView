package hu.bozgab.libra_view.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan(basePackages = {
        "hu.bozgab.libra_view.authentication.domain"
})
@EnableJpaRepositories(basePackages = {
        "hu.bozgab.libra_view.authentication.repository"
})
@ComponentScan(basePackages = {
        "hu.bozgab.libra_view.authentication.service", "hu.bozgab.libra_view.authentication.mapper", "hu.bozgab.libra_view.authentication.controller"
})
@Configuration
public class LibraConfiguration {

}
