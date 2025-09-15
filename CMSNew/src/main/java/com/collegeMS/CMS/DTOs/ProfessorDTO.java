package com.collegeMS.CMS.DTOs;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {

    private Long id;

    @Size(min = 5,max = 25,message="Prof Title Range must be within=[5,25]")
    private String title;
}
