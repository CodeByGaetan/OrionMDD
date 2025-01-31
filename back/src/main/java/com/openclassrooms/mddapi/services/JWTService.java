package com.openclassrooms.mddapi.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JWTService {

    @Autowired
    private JwtEncoder jwtEncoder;

    /**
     * Generate JWT token from user email
     * 
     * @param email of the user
     * @return JWT token to authenticate future requests
     */
    public String generateToken(String email) {

        Instant now = Instant.now();
        Instant expiration = now.plus(1, ChronoUnit.DAYS);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(expiration)
                .subject(email)
                .build();
        
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters
                .from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);

        return jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

}
