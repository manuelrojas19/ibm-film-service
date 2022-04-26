package com.ibm.academy.cms.filmservice.dto;

import com.ibm.academy.cms.filmservice.entity.Film;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "directors")
public class DirectorDto extends PersonDto {

    @JsonIgnore
    private static final long serialVersionUID = 6911393650696360788L;

}
