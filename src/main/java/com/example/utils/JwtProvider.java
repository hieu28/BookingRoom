package com.example.utils;

import com.example.models.entities.EmployeeEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtProvider {
    @Value("${jwt.jwtSecret}")
    private String SECRET_KEY;

    @Autowired

    private RedisTemplate<String, String> template;


    public RedisTemplate<String, String> getTemplate() {
        return template;
    }

    public void setTemplate(RedisTemplate<String, String> template) {
        this.template = template;
    }


//    public String getUsernameFromToken(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public Date getExpirationFromToken(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
//    }
//
//    public <T> T extractClaim(String token, Function<Claims, T> claimsResovler) {
//        final Claims claims = extractAllClaims(token);
//        return claimsResovler.apply(claims);
//    }


    public String getUsernameFromToken(String token) {
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

    public String generateToken(EmployeeEntity employee) {

        Map<String, Object> claims = new HashMap<>();

        String jwt = doGenerateToken(claims, employee.getEmail());
        template.opsForHash().get(jwt, employee.getId());
        return jwt;

    }

//    private String createToken(Map<String, Object> claims, String email) {
//
//        //template.opsForValue().set(String.valueOf(claims), employee.getEmail());
//        String jwt =  doGenerateToken(claims, employee.getEmail());
//        template.opsForValue().set(jwt, employee.getId());
//        return jwt;
//    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        Instant issuaAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuaAt.plus(3, ChronoUnit.HOURS);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date.from(issuaAt))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, EmployeeEntity employee) {

        final String username = getUsernameFromToken(token);
        return (username.equals(employee.getEmail()) && !isTokenExpired(token));
    }

}


