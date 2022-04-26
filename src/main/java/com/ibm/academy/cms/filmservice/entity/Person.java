package com.ibm.academy.cms.filmservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@MappedSuperclass
public abstract class Person extends AuditMetadata implements Serializable {

    private static final long serialVersionUID = -9195576735936618147L;

    @NotBlank
    @Size(max = 70)
    @Column(name = "name", columnDefinition = "TEXT")
    protected String name;

    @NotBlank
    @Size(max = 400)
    @Column(name = "description", columnDefinition = "TEXT")
    protected String description;

    @NotNull
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name = "born_date", columnDefinition = "DATE")
    protected LocalDate bornDate;

    @NotBlank
    @Size(max = 400)
    @Column(name = "born_place", columnDefinition = "TEXT")
    protected String bornPlace;

}
