package com.pragma.users_microservice.configuration.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;
  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
                                  @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain) throws ServletException, IOException {

    String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

    if (jwtToken != null){

      jwtToken = jwtToken.substring(7);
      DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

      String username = jwtUtils.extractUsername(decodedJWT);
      String authorities = jwtUtils.getSpecifiedClaim(decodedJWT, "authorities").asString();

      Collection<? extends GrantedAuthority> autoritiesList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

      SecurityContext context = SecurityContextHolder.getContext();
      Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, autoritiesList);

      context.setAuthentication(authentication);
      SecurityContextHolder.setContext(context);

    }

    filterChain.doFilter(request, response);
  }
}
