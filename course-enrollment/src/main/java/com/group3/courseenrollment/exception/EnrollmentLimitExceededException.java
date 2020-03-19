package com.group3.courseenrollment.exception;

public class EnrollmentLimitExceededException extends RuntimeException {
    public EnrollmentLimitExceededException(String msg,Throwable throwable){
        super(msg,throwable);
    }

    public EnrollmentLimitExceededException(String msg){
        super(msg);
    }
}
