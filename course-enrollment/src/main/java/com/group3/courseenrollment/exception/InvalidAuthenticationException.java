package com.group3.courseenrollment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;

@Slf4j
public class InvalidAuthenticationException extends AuthenticationException {
    public InvalidAuthenticationException(String msg, Throwable t) {
        super(msg, t);
        log.info(msg,t);
    }

    public InvalidAuthenticationException(String msg) {
        super(msg);
        log.info(msg);
    }
}
