package com.ibm.academy.cms.filmservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "actors_films")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder
@ToString(callSuper = true)
public class Cast implements Serializable {

    private static final long serialVersionUID = -5989787590057588436L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @ElementCollection
    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "actors_films_id"))
    @ToString.Exclude
    private List<String> roles;


}
