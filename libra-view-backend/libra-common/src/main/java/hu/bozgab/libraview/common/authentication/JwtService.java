package hu.bozgab.libraview.common.authentication;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import hu.bozgab.libraview.common.authentication.exception.JwtAuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@RequiredArgsConstructor
public class JwtService {

    public static final String KEY_ID = "ID";

    public static final String KEY_ROLES = "ROLES";

    private final String secretKey;

    @Getter
    private final Long tokenExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public List<String> extractRoles(String jwt) {
        return extractClaim(jwt, claims -> (List<String>) claims.get(KEY_ROLES));
    }

    public Long extractId(String token) {
        return extractClaim(token, claims -> Long.valueOf(claims.get(KEY_ID).toString()));
    }

    public String generateToken(LibraUser user) {
        return generateToken(new HashMap<>(), user);
    }

    public boolean isTokenValid(String jwt) {
        return !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return extractClaim(jwt, Claims::getExpiration).before(new Date());
    }

    private String generateToken(Map<String, Object> extraClaims, LibraUser user) {
        long currentTimeMillis = System.currentTimeMillis();
        extraClaims.put(KEY_ID, user.getId());
        return Jwts.builder()
                .claims(extraClaims)
                .subject(user.getUsername())
                .claim(KEY_ROLES, user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(role -> role.substring("ROLE_".length()))
                        .toArray())
                .issuedAt(new Date(currentTimeMillis))
                .expiration(new Date(currentTimeMillis + tokenExpiration * 1000))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    private <T> T extractClaim(String jwt, Function<Claims, T> claimResolver) {
        Claims claims = extractAllClaims(jwt);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        } catch(JwtException e) {
            throw new JwtAuthenticationException(e.getMessage());
        }
    }

    private SecretKey getSigningKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

}
