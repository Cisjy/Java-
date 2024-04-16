package com.example.station_springboot.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtsUtil {
    private static long expire = 60 * 60 * 24; // 24 hours

    private static String secret = "secret1231231231";

    public static String generateToken(String username) {
        Date now = new Date();
        Date expirateDate = new Date(now.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expirateDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
