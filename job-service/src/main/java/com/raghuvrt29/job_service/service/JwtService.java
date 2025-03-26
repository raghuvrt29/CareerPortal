package com.raghuvrt29.job_service.service;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private String secretKey;
    private JwtDecoder jwtDecoder;

    public JwtService() {
        Dotenv dotenv = Dotenv.configure().directory("../").load();

        this.secretKey = dotenv.get("JWT_SECRET_KEY");
        if(this.secretKey == null || this.secretKey.isEmpty()) {
            throw new IllegalStateException("JWT_SECRET_KEY is missing in .env file");
        }

        SecretKeySpec secretKeySpec = (SecretKeySpec) getKey();
        this.jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec).build();
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//    public boolean validateToken(String token){
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(getKey())
//                    .build().parseClaimsJws(token);
//            if(isTokenExpired(token)) return false;
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public Jwt decodeToken(String token){
        return jwtDecoder.decode(token);
    }

//    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    public String extractRole(String token) {
//        return (String) extractAllClaims(token).get("role");
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }

//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(getKey())
//                .build().parseClaimsJws(token).getBody();
//    }

}
