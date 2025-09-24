package hu.bozgab.libraview.master.controller;

import hu.bozgab.libraview.master.generated.api.UserApi;
import hu.bozgab.libraview.master.generated.model.AuthNResponse;
import hu.bozgab.libraview.master.generated.model.LoginRequest;
import hu.bozgab.libraview.master.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
@RestController
@RequestMapping
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public Mono<AuthNResponse> login(Mono<LoginRequest> loginRequest, ServerWebExchange exchange) {
        return loginRequest.flatMap(request -> userService.login(request));
    }

}
