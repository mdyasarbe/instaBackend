package com.interview.insta.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "QuickFoxJumpOverLazyDog";

    private static final int TOKEN_VALIDITY = 3600 * 5;

    public String getUsernameFromToken(String token) throws Exception {
        try {
            String claim = getClaimFromToken(token, Claims::getSubject);
            return claim;

        } catch (SignatureException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) throws Exception {
        final Claims claims;
        try {
            claims = getAllClaimsFromToken(token);
            return claimResolver.apply(claims);

        } catch (SignatureException ex) {
            throw ex;
        }
    }

    private Claims getAllClaimsFromToken(String token) throws Exception {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (SignatureException ex) {
            throw ex;
        }

    }

    public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
        try {
            final String username = getUsernameFromToken(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        } catch (SignatureException ex) {
            throw ex;
        }

    }

    private Boolean isTokenExpired(String token) throws Exception {
        try {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    } catch (SignatureException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Date getExpirationDateFromToken(String token) throws Exception {
        try {
            return getClaimFromToken(token, Claims::getExpiration);
        } catch (SignatureException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

}
