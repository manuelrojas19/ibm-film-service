package com.ibm.academy.cms.filmservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Film extends AuditMetadata implements Serializable {

    private static final long serialVersionUID = -7636978099139553319L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 70)
    @Column(name = "title", columnDefinition = "TEXT")
    private String title;

    @NotBlank
    @Size(max = 400)
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate date;

    @ManyToMany(
            mappedBy = "films",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Category> categories;

    @ManyToMany(
            mappedBy = "films",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Director> directors;

    @OneToMany(
            mappedBy = "film",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Cast> casts;

}
