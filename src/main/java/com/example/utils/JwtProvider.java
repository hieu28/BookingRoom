package com.example.utils;

import com.example.exceptions.JwtNotFound;
import com.example.models.responses.EmployeeResponse;
import com.example.services.impl.EmployeeService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Autowired
    private RedisTemplate<Object, Object> template;
    @Autowired
    private EmployeeService employeeService;

    public String getEmailFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResovler) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResovler.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return getExpirationFromToken(token).before(new Date());
    }

    public String generateToken(String email) {

        Map<String, Object> claims = new HashMap<>();
        String token = doGenerateToken(claims, email);
        template.opsForValue().set(token, email);

        return token;
    }

    private String doGenerateToken(Map<String, Object> claims, String email) {

        Instant issuaAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuaAt.plus(3, ChronoUnit.HOURS);

        List<EmployeeResponse> employee = employeeService.searchEmail(email);
        long userid = employee.get(0).getId();

        return Jwts.builder().setClaims(claims).setSubject(email).setIssuedAt(Date.from(issuaAt))
                .setExpiration(Date.from(expiration)).claim("user-id", userid)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, String email) {
        return (email.equals(template.opsForValue().get(token)) && !isTokenExpired(token));
    }

    public Boolean CheckToken(String authorizationHeader) {
        try {

            String token = authorizationHeader.substring(7);
            String email = getAllClaimsFromToken(token).getSubject();

            return validateToken(token, email);
        } catch (SignatureException e) {
            throw new JwtNotFound();
        } catch (MalformedJwtException e) {
            throw new JwtNotFound();
        } catch (ExpiredJwtException ex) {
            throw new JwtNotFound();
        }

    }

}