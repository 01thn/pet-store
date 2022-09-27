package com.cinemastore.privateservice.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "firstName", "lastName", "dateOfBirth", "dateOfDeath"})
@ToString(of = {"id", "firstName", "lastName", "dateOfBirth", "dateOfDeath"})
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "date_of_death")
    private LocalDate dateOfDeath;

    @ManyToMany
    @JoinTable(
            name = "person_position",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id")
    )
    private Set<Position> positions = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "person_country",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id")
    )
    private Set<Country> countries = new HashSet<>();

    @ManyToMany(mappedBy = "authors")
    private Set<Film> filmsAuthor = new HashSet<>();

    @ManyToMany(mappedBy = "operators")
    private Set<Film> filmsOperator = new HashSet<>();

    @ManyToMany(mappedBy = "actors")
    private Set<Film> filmsActor = new HashSet<>();

    @ManyToMany(mappedBy = "authors")
    private Set<Film> seriesAuthor = new HashSet<>();

    @ManyToMany(mappedBy = "operators")
    private Set<Film> seriesOperator = new HashSet<>();

    @ManyToMany(mappedBy = "actors")
    private Set<Film> seriesActor = new HashSet<>();
}
