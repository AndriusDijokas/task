package com.workforpica.task.security;

import com.workforpica.task.configuration.AppProperties;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
//ToDo: fix deprecated stuff,update JWT dependencies
@Service
public class TokenProvider {

    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);


    private AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken, HttpServletRequest httpServletRequest) throws ExpiredJwtException {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(appProperties.getAuth().getTokenSecret())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token",ex.fillInStackTrace());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token",ex.fillInStackTrace());
            httpServletRequest.setAttribute("custom-msg", "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token",ex.fillInStackTrace());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.",ex.fillInStackTrace());
        }
        return false;
    }

}
