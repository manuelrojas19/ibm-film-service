package com.ibm.academy.cms.filmservice.dto;

import com.ibm.academy.cms.filmservice.entity.Film;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ActorDto extends PersonDto implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 1690442391318535350L;

    private List<Film> films = new ArrayList<>();

}
