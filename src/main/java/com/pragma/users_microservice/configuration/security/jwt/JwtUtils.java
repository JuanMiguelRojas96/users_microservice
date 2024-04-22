package com.pragma.users_microservice.configuration.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pragma.users_microservice.configuration.security.exceptions.SecurityConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
public class JwtUtils {

  @Value("${private-key}")
  private String privateKey;

  @Value("${user-generator}")
  private String userGenerator;


  public String createToken(Authentication authentication) {
    Algorithm algorithm = Algorithm.HMAC256(privateKey);

    String username = authentication.getPrincipal().toString();
    String authorities = authentication.getAuthorities()
        .stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

    return JWT.create()
        .withIssuer(userGenerator)
        .withSubject(username)
        .withClaim("authorities",authorities)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis()+1800000))
        .withJWTId(UUID.randomUUID().toString())
        .withNotBefore(new Date(System.currentTimeMillis()))
        .sign(algorithm);
  }


  public DecodedJWT validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(privateKey);

      JWTVerifier verifier = JWT.require(algorithm)
          .withIssuer(userGenerator)
          .build();

      return verifier.verify(token);

    } catch (JWTVerificationException exception) {
      throw new JWTVerificationException(SecurityConstants.TOKEN_INVALID);
    }
  }


  public String extractUsername(DecodedJWT decodedJWT) {

    return decodedJWT.getSubject();

  }

  public Claim getSpecifiedClaim(DecodedJWT decodedJWT, String claimName) {

    return decodedJWT.getClaim(claimName);

  }

  public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT) {

    return decodedJWT.getClaims();
  }
}
