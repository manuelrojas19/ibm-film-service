package com.ibm.academy.cms.filmservice.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CastDto {

    private ActorDto actor;

    private List<String> roles = new ArrayList<>();
}
