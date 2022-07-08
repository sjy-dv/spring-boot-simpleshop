package ex.shop.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class Jwt {

    @Value("${access_key}")
    String AccessKey;
    @Value("${refresh_key}")
    String RefreshKey;

    private final Long expiredTime = 1000 * 300L; // 5minute
    private final Long rexpiredTime = 1000 * 60L * 60L * 24L * 30L;

    public String CreateToken(String userId, String nickname) {
        Date now = new Date();
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("user_id", userId);
        payloads.put("nickname", nickname);
        return Jwts.builder()
                .setSubject("authorization")
                .setHeader(createHeader())
                .setClaims(payloads)
                .setExpiration(new Date(now.getTime() + expiredTime))
                .signWith(SignatureAlgorithm.HS256, AccessKey)
                .compact();
    }

    public String CreateRToken(String userId) {
        Date now = new Date();
        Map<String, Object> payloads = new HashMap<>();
        payloads.put("user_id", userId);
        return Jwts.builder()
                .setSubject("rauthorization")
                .setHeader(createHeader())
                .setClaims(payloads)
                .setExpiration(new Date(now.getTime() + rexpiredTime))
                .signWith(SignatureAlgorithm.HS256, RefreshKey)
                .compact();
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(AccessKey).parseClaimsJws(token).getBody();
    }

    public String VerifyToken(String token) {
        return (String) getClaims(token).get("user_id");
    }

    private Claims getRClaims(String token) {
        return Jwts.parser().setSigningKey(RefreshKey).parseClaimsJws(token).getBody();
    }

    public String VerifyRToken(String token) {
        return (String) getRClaims(token).get("user_id");
    }

}