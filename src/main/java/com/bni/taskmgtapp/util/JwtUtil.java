package com.bni.taskmgtapp.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import org.springframework.stereotype.Service;

@Service
public class JwtUtil {
    //Super Secret Key for signing JWTs in production use a proper secret from config
    private static final String SECRET = "SGFycnlQb3R0ZXImR2lubnlXZWFzbGV5QXJlVGhlQmVzdENvdXBsZUV2ZXI=";
    private static final Key SIGNING_KEY = new SecretKeySpec(
        Base64.getDecoder().decode(SECRET),
        SignatureAlgorithm.HS256.getJcaName());

    public String createToken (String username){
        return Jwts.builder()
            .setSubject(username)
            .signWith(SIGNING_KEY)
            .compact();
    }
    public String validateToken(String Token){
        return Jwts.parserBuilder()
            .setSigningKey(SIGNING_KEY)
            .build()
            .parseClaimsJws(Token)
            .getBody()
            .getSubject();
    }
}
