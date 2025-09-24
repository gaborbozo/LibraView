package hu.bozgab.libraview.master.service;

import java.util.List;

import hu.bozgab.libraview.common.authentication.LibraUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;


@Configuration
class UserDetailsConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        // TODO
        // Temporary consturct the user entity
        // Later on custom implementation ReactiveUserDetailsService for and the findByUsername
        var user = new LibraUser(
                "gabor",
                passwordEncoder().encode("pass"),
                List.of(new SimpleGrantedAuthority("ADMIN")),
                1L
        );
        return new MapReactiveUserDetailsService(user) {
            @Override
            public Mono<UserDetails> findByUsername(String username) {
                return super.findByUsername(username).flatMap(user ->
                        Mono.just(new LibraUser(
                                user.getUsername(),
                                user.getPassword(),
                                user.getAuthorities(),
                                1L
                        ))
                );
            }
        };
    }

}