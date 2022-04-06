package com.ibm.academy.cms.filmservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public abstract class PersonDto extends BaseEntityDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date bornDate;

    @NotBlank
    private String bornPlace;
}
