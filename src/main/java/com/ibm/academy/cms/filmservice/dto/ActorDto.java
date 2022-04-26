package com.ibm.academy.cms.filmservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.academy.cms.filmservice.entity.Film;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.server.core.Relation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "actors")
public class ActorDto extends PersonDto implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 1690442391318535350L;

    private List<Film> films = new ArrayList<>();

}
