package hu.bozgab.movie.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("hu.bozgab.movie.domain")
@EnableJpaRepositories(basePackages = {"hu.bozgab.movie.repository"})
@ComponentScan(basePackages = {"hu.bozgab.movie.controller", "hu.bozgab.movie.service", "hu.bozgab.movie.mapper"})
@Configuration
public class MovieConfiguration {
}
