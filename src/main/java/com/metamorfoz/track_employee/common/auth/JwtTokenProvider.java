package com.metamorfoz.track_employee.common.auth;

import com.metamorfoz.track_employee.common.exceptions.TokenException;
import com.metamorfoz.track_employee.domain.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${security.secret-key}")
    private String secretKeyString;

    @Value("${security.token.expiration}")
    private long validityInMilliseconds = 3600000;

    private final MyUserDetail myUserDetails;

    private SecretKey secretKey;

    @PostConstruct
    protected void init() {
        this.secretKey = Keys.hmacShaKeyFor(secretKeyString.getBytes(StandardCharsets.UTF_8));
    }

    public String  generateToken(int bindedWorkerId,String username, Set<UserRole> userRoles){

        ClaimsBuilder claims = Jwts.claims().setId(String.valueOf(bindedWorkerId)).setSubject(username);
        claims.add("auth", userRoles.stream().map(s -> new SimpleGrantedAuthority(s.getRole())).collect(Collectors.toList()));
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims.build())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey,SignatureAlgorithm.HS256)
                .compact();
    }


    public Authentication getAuthentication(String token) {
        UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String getWorkerId(String token) {
        token = token.substring(7);
        return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getId();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token){
        try {
            return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getExpiration().after(new Date(System.currentTimeMillis()));
        } catch (JwtException | IllegalArgumentException e) {
            throw new TokenException("Expired or invalid JWT token");
        }
    }

}
