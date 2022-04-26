package com.ibm.academy.cms.filmservice.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntityDto extends RepresentationModel<BaseEntityDto> {

    @Null
    private Long id;

    @Null
    private Long version;

    @Null
    private LocalDateTime createdAt;

    @Null
    private LocalDateTime lastModifiedAt;

    @Null
    private String createdBy;

    @Null
    private String lastModifiedBy;

}
