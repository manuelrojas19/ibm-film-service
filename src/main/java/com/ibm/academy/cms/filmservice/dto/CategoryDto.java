package com.ibm.academy.cms.filmservice.dto;

import com.ibm.academy.cms.filmservice.entity.Film;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.minidev.json.annotate.JsonIgnore;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryDto extends BaseEntityDto implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 2524030402252680288L;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

}
