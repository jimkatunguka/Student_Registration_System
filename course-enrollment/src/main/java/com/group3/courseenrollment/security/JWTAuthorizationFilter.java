package com.group3.courseenrollment.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.group3.courseenrollment.configuration.ApplicationProperties;
import com.group3.courseenrollment.domain.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author SARFO PHILIP
 * {@link JWTAuthenticationFilter} Filter extends Spring Security's filter and
 * changes the behaviour by adding JWT authorization before client can consume
 * resource server
 */
@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private ApplicationProperties applicationProperties;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager,ApplicationProperties applicationProperties) {
        super(authenticationManager);
        this.applicationProperties = applicationProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Gets the header from the request
        String header = request.getHeader(applicationProperties.getJwt().getHeaderString());

        // Validates header
        if (header == null || !header.startsWith(applicationProperties.getJwt().getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        // Verifies token again
        Optional<UsernamePasswordAuthenticationToken> authentication
                = getAuthentication(request);

        
        authentication.ifPresent(auth->{
        	SecurityContextHolder.getContext().setAuthentication(auth);
        });



        // Continue ...
        chain.doFilter(request, response);
    }

    /**
     * getAuthentication verifies user's Jwt token
     * @param request
     * @return
     */
    @Transactional
    private Optional<UsernamePasswordAuthenticationToken> getAuthentication(HttpServletRequest request){
        String token = request.getHeader(applicationProperties.getJwt().getHeaderString());
        // Token is checked
        if(token != null){
            // parse the token.
            String user = JWT.require(Algorithm.HMAC512(applicationProperties.getJwt().getSecret().getBytes()))
                    .build()
                    .verify(token.replace(applicationProperties.getJwt().getTokenPrefix(), ""))
                    .getSubject();


            if (user != null){
                List<GrantedAuthority> mapRolesToGrantedAuthorities =
                                    getClaim(token.replace(applicationProperties.getJwt().getTokenPrefix(), "")).asList(String.class)
                                    .stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList());

                return Optional.of(new UsernamePasswordAuthenticationToken(user,null,mapRolesToGrantedAuthorities));
            }
            return Optional.empty();

        }
        return Optional.empty();
    }

    /**
     * Method Decodes and retrieve user roles
     * @param token
     * @return
     */
    private Claim getClaim(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("roles");
    }
}
