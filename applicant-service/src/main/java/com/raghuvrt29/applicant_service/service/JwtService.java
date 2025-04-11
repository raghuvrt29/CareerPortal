package com.raghuvrt29.applicant_service.service;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String ENV_FILE = "../.env";
    private static final String ENV_VARIABLE = "JWT_SECRET_KEY";
    private static final String ROLE = "ROLE_APPLICANT";

    private final String secretKey;
    private final JwtDecoder jwtDecoder;

    public JwtService() throws IOException {
        this.secretKey = getOrGenerateSecretKey();

        SecretKeySpec secretKeySpec = (SecretKeySpec) getKey();
        this.jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec).build();
    }

    private String getOrGenerateSecretKey() throws IOException {
        Dotenv dotenv = Dotenv.configure().directory("../").load();

        String secretKey = dotenv.get(ENV_VARIABLE);
        if(secretKey != null && !secretKey.isEmpty()){
            return secretKey;
        }

        Path envPath = Paths.get(ENV_FILE);
        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("HmacSHA256");
            secretKey = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());
            String newEnvEntry = ENV_VARIABLE + "=" + secretKey;
            System.out.println(newEnvEntry);
            Files.write(envPath, (newEnvEntry + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            return secretKey;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating key",e);
        }
    }

    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String userId, String name) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", ROLE);
        claims.put("name", name);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    }

    public Jwt decodeToken(String token) {
        return jwtDecoder.decode(token);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver){
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
