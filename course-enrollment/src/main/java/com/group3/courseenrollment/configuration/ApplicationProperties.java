package com.group3.courseenrollment.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("com.group3.courseenrollment")
public class ApplicationProperties {

    private JWTProperties jwt = new JWTProperties();
    private Integer enrollmentLimitPerStudent;
    private String appDomainName;

    /**
     * Jwt Properties
     */
    @Data
    public static class JWTProperties{
        private String issuer;
        private String secret;
        private Long expirationTime;
        private String tokenPrefix;
        private String headerString;
    }
}
