package com.collegeMS.CMS.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionRecordDTO {

    private Long id;

    @DecimalMin(value="10000.00",message="Admission Fee >= 10000.00")
    @DecimalMax(value="100000.00",message="Admission Fee < 100000.00")
    @NotNull(message="Please Enter the value")
    private Integer fees;

    @NotNull(message="StudentId can not be null, Please Enter the student Id")
    private Long StudentId;
}
