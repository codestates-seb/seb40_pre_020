package com.SEB_Pre_020.demo.config.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JwtTokenizer {

    private String secretKey = "iv1idjidIlgi1hii1dijhciIj1!ih|giI";
    /** 코드에서 환경변수로 빼내 숨기는 방법 적용해야 함 **/

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }   // 객체 초기화, secretKey를 Base64로 인코딩한다.

    public String encodeBase64SecretKey(String secretKey) {
        return Encoders.BASE64.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(Map<String, Object> claims,
                                      String subject,
                                      Date expiration,
                                      String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);
        //  Base64 형식 Secret Key 문자열을 이용해 Key(java.security.Key) 객체 얻기


        return Jwts.builder()
                .setClaims(claims) // 인증된 사용자와 관련된 정보 추가
                .setSubject(subject) // JWT 제목 추가
                .setIssuedAt(Calendar.getInstance().getTime()) // JWT 발행 일자 설정
                .setExpiration(expiration) // JWT 만료일시 지정(파라미터인 expiration은 date 타입)
                .signWith(key) // 서명을 위한 Key 객체 생성
                .compact(); // JWT 생성과 직렬화
    } // 인증된 사용자에게 JWT 최초 발급해주기 위한 JWT 생성 메서드

    public String generateRefreshToken(String subject, Date expiration, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Calendar.getInstance().getTime())
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    } // RefreshToken 생성 메서드

    private Key getKeyFromBase64EncodedKey(String base64EncodedSecretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        // Base64 형식으로 인코딩 된 Secret Key를 디코딩 한 후, byte array를 반환
        Key key = Keys.hmacShaKeyFor(keyBytes);
        // key byte array를 기반으로 HMAC 알고리즘을 적용한 Key(java.security.Key) 객체를 생성

        return key;
    } // JWT 서명에 사용하는 SecretKey 생성 메서드

    public void verifySignature(String jws, String base64EncodedSecretKey) {
        Key key = getKeyFromBase64EncodedKey(base64EncodedSecretKey);

        Jwts.parserBuilder()
                .setSigningKey(key)     // (1)
                .build()
                .parseClaimsJws(jws);   // (2)
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
