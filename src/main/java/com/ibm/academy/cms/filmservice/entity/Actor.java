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
@ToString(callSuper = true)
public class Actor extends Person implements Serializable {

    private static final long serialVersionUID = -6076638049842915616L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @OneToMany(
            mappedBy = "actor",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Cast> casts;

}
