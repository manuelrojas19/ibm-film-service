package com.ibm.academy.cms.filmservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Null;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntityDto extends RepresentationModel<BaseEntityDto> {

    @Null
    private Long id;

    @Null
    private Long version;

    @Null
    private Date createdAt;

    @Null
    private Date lastModifiedAt;

    @Null
    private String createdBy;

    @Null
    private String lastModifiedBy;

}
