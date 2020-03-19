package com.group3.courseenrollment.dto;


import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class OfferingDto {
    @NonNull
    private String courseId;

    @NonNull
    private String blockId;

    @NonNull
    private List<Long> sections;
}
