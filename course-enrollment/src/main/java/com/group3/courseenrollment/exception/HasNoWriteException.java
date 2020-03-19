package com.group3.courseenrollment.exception;

public class HasNoWriteException extends RuntimeException {
    public HasNoWriteException(String msg,Throwable throwable){
        super(msg,throwable);
    }

    public HasNoWriteException(String msg){
        super(msg);
    }
}
