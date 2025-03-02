package hu.bozgab.libra_view.authentication.controller;

import hu.bozgab.libra_view.authentication.dto.LoginRequest;
import hu.bozgab.libra_view.authentication.dto.LoginResponse;
import hu.bozgab.libra_view.authentication.service.AuthenticationService;
import hu.bozgab.libra_view.authentication.service.JwtService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(LoginResponse
                .builder()
                .token(jwtService.generateToken(authenticationService.authenticate(request)))
                .expiresIn(jwtService.getJwtExpiration()).build()
        );
    }

}

