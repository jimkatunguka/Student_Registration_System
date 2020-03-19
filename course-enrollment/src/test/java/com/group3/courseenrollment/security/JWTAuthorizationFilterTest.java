package com.group3.courseenrollment.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.Assert.*;


public class JWTAuthorizationFilterTest {


    @Test
    public void claim(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwaGlsIiwicm9sZXMiOlsiUk9MRV9BRE1JTiJdLCJpc3MiOiJ3aW5kLWdyb3VwIiwiZXhwIjoxNTg1MzgwMDE2LCJpYXQiOjE1ODQ1MTYwMTZ9.PMoshzdpQIjji8iMGugr7ukq5xHzPc9C09EQhSdtCwp6FC4VWhH58zqrUsw6iVu75jXZefVvoEJ0ZGuP8rhuqw";
        Claim claim = getClaim(token);
        List<String> list = claim.asList(String.class);
        assertNotNull(claim);
    }


    private Claim getClaim(String token){
        List<String> roles = null;
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim("roles");
    }

}