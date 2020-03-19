package com.group3.courseenrollment.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;


@Data
public class SectionDto {
    @NonNull
    private List<Long> enrollmentCodes;

    @NonNull
    private Long facultyId;
}
