package com.ibm.academy.cms.filmservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
public class ActorRole implements Serializable {

    @EmbeddedId
    private ActorRolePK id = new ActorRolePK();

    @MapsId("actorId")
    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;


    @MapsId("filmId")
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    private String role;

    @Data
    @EqualsAndHashCode
    @Embeddable
    public static class ActorRolePK implements Serializable {

        private static final long serialVersionUID = -3008825887688715397L;

        private Long actorId;

        private Long filmId;

    }
}
