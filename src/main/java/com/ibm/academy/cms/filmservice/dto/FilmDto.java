package com.ibm.academy.cms.filmservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(collectionRelation = "films")
public class FilmDto extends BaseEntityDto implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = -5772643405481823484L;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate date;

    private List<DirectorDto> directors = new ArrayList<>();

    private List<CategoryDto> categories = new ArrayList<>();

    private Set<CastDto> casts = new HashSet<>();

}
