package backend.speedspringstudy.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    @Value("${jwt.access-token-secret}}")
    private String accessTokenSecret;

    @Value("${jwt.refresh-token-secret}")
    private String refreshTokenSecret;

    private final long ACCESS_EXPIRED_TIME = 1000 * 60 * 60;
    private final long REFRESH_EXPIRED_TIME = 1000 * 60 * 60 * 24 * 7;

    // 액세스 토큰 생성
    public String generateAccessToken(String email, Long memberId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("memberId", memberId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRED_TIME))
                .signWith(SignatureAlgorithm.HS256, accessTokenSecret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    // 리프레시 토큰 생성
    public String generateRefreshToken(String email, Long memberId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("memberId", memberId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRED_TIME))
                .signWith(SignatureAlgorithm.HS256, refreshTokenSecret.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    // 액세스 토큰 검증
    public boolean isAccessTokenValid(String token) {
        return validate(token, accessTokenSecret);
    }

    // 토큰 유효하면 유저 반환
    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(accessTokenSecret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    private boolean validate(String token, String secret) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(accessTokenSecret.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if (bearer != null && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}