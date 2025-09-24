package hu.bozgab.libraview.cineregistry.configuration.security;

import java.util.List;

import hu.bozgab.libraview.common.authentication.JwtService;
import hu.bozgab.libraview.common.authentication.JwtToken;
import hu.bozgab.libraview.common.authentication.LibraUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@Component
public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {

    private final JwtService jwtService;
    private static final String BEARER = "Bearer ";

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(header -> header.startsWith(BEARER))
                .map(header -> header.substring(BEARER.length()))
                .map(token -> new JwtToken(token, createUserDetails(token)));
    }

    private LibraUser createUserDetails(String token) {
        String username = jwtService.extractUsername(token);
        return new LibraUser(
                username,
                "",
                createAuthorities(token),
                jwtService.extractId(token)
        );
    }

    private List<SimpleGrantedAuthority> createAuthorities(String token) {
        return jwtService.extractRoles(token).stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

}
