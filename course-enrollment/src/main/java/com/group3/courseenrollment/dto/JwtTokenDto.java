package com.group3.courseenrollment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JwtTokenDto {
    private String accessToken;

    public JwtTokenDto(@JsonProperty String accessToken){
        this.accessToken = accessToken;
    }
}
