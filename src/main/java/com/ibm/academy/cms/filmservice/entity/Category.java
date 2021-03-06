package com.ibm.academy.cms.filmservice.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Category extends AuditMetadata implements Serializable {

    private static final long serialVersionUID = 3561466135103933380L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotBlank
    @Size(max = 70)
    @Column(name = "name", columnDefinition = "TEXT", unique = true)
    private String name;

    @NotBlank
    @Size(max = 400)
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "categories_films",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Film> films = new HashSet<>();

}
