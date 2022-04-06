package com.ibm.academy.cms.filmservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ActorRoleRelationDto {

    private ActorDto actor;

    private List<String> roles;

}
