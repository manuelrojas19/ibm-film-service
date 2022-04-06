package com.ibm.academy.cms.filmservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Film extends AuditMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private Date date;

    @OneToMany(mappedBy = "film", fetch = FetchType.EAGER)
    private List<ActorRole> actorsAndRoles = new ArrayList<>();

    @ManyToMany(mappedBy = "films", fetch = FetchType.EAGER)
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "films", fetch = FetchType.EAGER)
    private Set<Director> directors = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        if (!super.equals(o)) return false;
        Film film = (Film) o;
        return Objects.equals(getTitle(), film.getTitle()) && Objects.equals(getDescription(), film.getDescription()) && Objects.equals(getDate(), film.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getDescription(), getDate());
    }
}
