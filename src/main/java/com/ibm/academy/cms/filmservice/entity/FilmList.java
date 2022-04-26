package com.ibm.academy.cms.filmservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
public class FilmList extends AuditMetadata implements Serializable {

    private static final long serialVersionUID = -5158465652788366787L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(max = 70)
    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @NotBlank
    @Size(max = 400)
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotBlank
    @Size(max = 70)
    @Column(name = "ownerUsername", columnDefinition = "TEXT")
    private String ownerUsername;

    @OneToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @EqualsAndHashCode.Exclude
    private List<Film> films;
}
