package hu.bozgab.shared.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("hu.bozgab.shared.authentication.domain")
@EnableJpaRepositories(basePackages = { "hu.bozgab.shared.authentication.repository" })
@ComponentScan(basePackages = { "hu.bozgab.shared.authentication.service", "hu.bozgab.shared.authentication.mapper" })
@Configuration
public class SharedConfiguration {

}
