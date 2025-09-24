package hu.bozgab.libraview.master.service;

import java.math.BigDecimal;

import hu.bozgab.libraview.common.authentication.JwtService;
import hu.bozgab.libraview.common.authentication.LibraUser;
import hu.bozgab.libraview.common.authentication.exception.JwtAuthenticationException;
import hu.bozgab.libraview.master.generated.model.AuthNResponse;
import hu.bozgab.libraview.master.generated.model.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Service
public class UserService {

    private final JwtService jwtService;

    private final ReactiveUserDetailsService userDetailsService;

    public Mono<AuthNResponse> login(LoginRequest loginRequest) {
        return userDetailsService.findByUsername(loginRequest.getUsername())
                .flatMap(user -> {
                    AuthNResponse response = new AuthNResponse();
                    response.setExpiration(BigDecimal.valueOf(jwtService.getTokenExpiration()));
                    response.setToken(jwtService.generateToken((LibraUser) user));
                    return Mono.just(response);
                })
                .switchIfEmpty(Mono.error(new JwtAuthenticationException(HttpStatus.UNAUTHORIZED.name())));
    }

}
