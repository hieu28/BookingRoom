package com.example.room.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    @Autowired
    private RedisTemplate redisTemplate;

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration (String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public<T> T extractClaim(String token, Function<Claims,T> claimsResovler){
        final Claims claims = extractAllClaims(token);
        return claimsResovler.apply(claims);
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims =  new HashMap<>();
        String jwt = createToken(claims, userDetails.getUsername());
        redisTemplate.opsForValue().set(jwt,userDetails.getUsername());
        return jwt;
    }

    private String createToken(Map<String,Object> claims, String subject){
        Instant issuaAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuaAt.plus(3, ChronoUnit.HOURS);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date.from(issuaAt))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        boolean isValidToken = (redisTemplate.opsForValue().get(token) == username);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token)) ;
    }
}
