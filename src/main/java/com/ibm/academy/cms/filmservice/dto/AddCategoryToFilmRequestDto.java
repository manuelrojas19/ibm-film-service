package com.ibm.academy.cms.filmservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddCategoryToFilmRequestDto {

    @NotNull
    private Long categoryId;

}
