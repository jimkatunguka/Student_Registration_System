package com.group3.courseenrollment.dto;

import com.group3.courseenrollment.domain.Enrollment;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Max;
import java.util.List;

@Data
public class StudentEnrollmentDto {

    @NonNull
    private List<Enrollment> enrollments;

    public StudentEnrollmentDto(List<Enrollment> enrollments){
        this.enrollments = enrollments;
    }
}
