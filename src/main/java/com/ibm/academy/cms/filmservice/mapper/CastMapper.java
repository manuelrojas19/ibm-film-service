package com.ibm.academy.cms.filmservice.mapper;

import com.ibm.academy.cms.filmservice.dto.CastDto;
import com.ibm.academy.cms.filmservice.entity.Cast;
import org.mapstruct.Mapper;

@Mapper(uses = {DataMapper.class})
public interface CastMapper {
    CastDto toDto(Cast cast);

    Cast toEntity(CastDto actorRoleDto);
}