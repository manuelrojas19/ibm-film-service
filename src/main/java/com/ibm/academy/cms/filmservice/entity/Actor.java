package com.ibm.academy.cms.filmservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Actor extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @OneToMany(mappedBy = "film", fetch = FetchType.LAZY)
    private List<ActorRole> actorRoles = new ArrayList<>();

}
