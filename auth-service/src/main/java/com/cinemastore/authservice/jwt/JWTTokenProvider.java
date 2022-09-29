package com.cinemastore.authservice.jwt;

import com.cinemastore.authservice.dto.RoleResponseDto;
import com.cinemastore.authservice.exception.JwtAuthenticationException;
import com.cinemastore.authservice.mapper.RoleMapper;
import com.cinemastore.authservice.security.UserSecurityDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
public class JWTTokenProvider {
    @Value("${jwt.token.secret}")
    private String jwtSecret;

    @Value("${jwt.token.expired}")
    private long jwtExpirationInMs;

    private final UserSecurityDetailsService detailsService;

    private final RoleMapper roleMapper;

    public JWTTokenProvider(UserSecurityDetailsService detailsService, RoleMapper roleMapper) {
        this.detailsService = detailsService;
        this.roleMapper = roleMapper;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    protected void init() {
        jwtSecret = Base64.getEncoder().encodeToString(jwtSecret.getBytes());
    }

    public String generateToken(String username, RoleResponseDto role) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roleMapper.responseDtoToEntity(role));

        Date now = new Date();
        Date validity = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = detailsService.loadUserByUsername(getUserUsernameFromJWT(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserUsernameFromJWT(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    @SneakyThrows
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }
}
