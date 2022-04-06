package com.ibm.academy.cms.filmservice.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AddActorToFilmRequestDto {

    @NotNull
    private Long actorId;

    @NotNull
    private List<String> roles;
}
