package hu.bozgab.libra_view.authentication.service;

import hu.bozgab.libra_view.authentication.dto.LibraUserDTO;
import hu.bozgab.libra_view.authentication.dto.LoginRequest;
import hu.bozgab.libra_view.authentication.mapper.UserMapper;
import hu.bozgab.libra_view.authentication.repository.LibraUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final LibraUserRepository libraUserRepository;

    private final UserMapper userMapper;

    public LibraUserDTO authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        return userMapper.mapToUserDTO(
                libraUserRepository.findByUsername(loginRequest.getUsername())
                        .orElseThrow()
        );
    }

}
