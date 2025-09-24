package hu.bozgab.libraview.cineregistry.configuration.security;

import hu.bozgab.libraview.common.authentication.LibraUser;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import reactor.core.publisher.Mono;


public class LibraSecurityContext {

    public static Mono<LibraUser> getCurrentUser() {
        return ReactiveSecurityContextHolder
                .getContext().flatMap(
                        ctx -> Mono.just((LibraUser) ctx.getAuthentication().getPrincipal())
                );
    }

}
