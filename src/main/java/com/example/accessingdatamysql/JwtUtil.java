package com.example.accessingdatamysql;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {
    // 암호 키
    private final String SECRET_KEY = "8ad3081f4ea3a95d2cee4db09ed1ffae9deb26d34005a17504e475e6d840881d20759085d5bc9197278b1eee9598359726ee5e5abe5997e3066372a929e358c1";

    // 토큰에서 이메일 추출
    public String extractEmail(String token){
        return extractClaim(token, Claims::getSubject);
    }

    // 토큰이 만료 되었는지?
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //token에서 추출된 Claim에서 특정 Claim을 추출
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //token에서 모든 Claim을 추출
    public Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 토큰 생성
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject) // 이메일 저장
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간 유효
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // HS256 알고리즘 사용, SECRET_KEY로 서명
                .compact();
    }

    // 토큰 유효성 검사
    public Boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }
}
