package com.ibm.academy.cms.filmservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class Director extends Person implements Serializable {

    private static final long serialVersionUID = 6503449542150782398L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "directors_films",
            joinColumns = @JoinColumn(name = "director_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Film> films;

}
