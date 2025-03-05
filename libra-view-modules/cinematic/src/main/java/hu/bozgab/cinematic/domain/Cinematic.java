package hu.bozgab.cinematic.domain;

import java.util.Date;
import java.util.Set;

import hu.bozgab.shared.authentication.domain.LibraUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "CINEMATIC")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DISCRIMINATOR", discriminatorType = DiscriminatorType.STRING)
public class Cinematic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TMDB_ID", unique = true)
    private Long tmdbId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "RELEASE_DATE")
    private Date releaseDate;

    @Column(name = "OVERVIEW", length = 5000)
    private String overview;

    @Column(name = "POSTER_PATH")
    private String posterPath;

    @Column(name = "BACKDROP_PATH")
    private String backdropPath;

    @Column(name = "VOTE_AVERAGE")
    private Double voteAverage;

    @Column(name = "VOTE_COUNT")
    private Integer voteCount;

    @Column(name = "POPULARITY")
    private Double popularity;

    @ManyToMany
    @JoinTable(
            name = "CINEMATIC_GENRE",
            joinColumns = @JoinColumn(name = "CINEMATIC_ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
    private Set<Genre> genres;

    /*
        Defined on the cinematic side to maintain a loose coupling between the Libra app and the module.
     */
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    @JoinTable(name = "user_cinematic",
            joinColumns = { @JoinColumn(name = "userId") },
            inverseJoinColumns = { @JoinColumn(name = "cinematicId") })
    private Set<LibraUser> users;

}
