package com.celso.taskapp.login.security;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.celso.taskapp.login.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${celso.app.jwtSecret}")
    private String jwtSecret;

    @Value("${celso.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${celso.app.jwtCookieName}")
    private String jwtCookie;

    public String getJwtFromCookies(final HttpServletRequest request) {
        final Cookie cookie = WebUtils.getCookie(request, jwtCookie);
        if (cookie != null) {
            return cookie.getValue();
        } else {
            return null;
        }
    }

    public ResponseCookie generateJwtCookie(final UserDetailsImpl userPrincipal) {
        final String jwt = generateTokenFromUsername(userPrincipal.getUsername());
        return ResponseCookie.from(jwtCookie, jwt).path("/api").maxAge(24 * 60L * 60).httpOnly(true).build();
    }

    public String getUserNameFromJwtToken(final String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(final String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (final SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (final MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (final ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (final UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (final IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String generateTokenFromUsername(final String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }
}
