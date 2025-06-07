package hu.bozgab.shared.authentication.service;

import hu.bozgab.shared.authentication.dto.LibraUserDTO;
import hu.bozgab.shared.authentication.exception.LibraAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class LibraUserContext extends SecurityContextHolder {

    public LibraUserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) {
            throw new LibraAuthenticationException("Authentication is null");
        }
        if(!authentication.isAuthenticated()) {
            throw new LibraAuthenticationException("User is not authenticated");
        }

        return (LibraUserDTO) authentication.getPrincipal();
    }

}
