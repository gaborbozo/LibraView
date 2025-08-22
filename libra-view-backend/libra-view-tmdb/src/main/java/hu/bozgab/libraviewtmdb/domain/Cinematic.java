package hu.bozgab.libraviewtmdb.domain;

import java.time.Instant;

import hu.bozgab.LibraViewTMDB.generated.model.CinematicType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Table("CINEMATIC")
public class Cinematic {

    @Column("DISCRIMINATOR")
    private CinematicType discriminator;

    @Id
    @Column("ID")
    private Long id;

    @Column("TMDB_ID")
    private Long tmdbId;

    @Column("TITLE")
    private String title;

    @Column("RELEASE_DATE")
    private Instant releaseDate;

    @Column("OVERVIEW")
    private String overview;

    @Column("POSTER_PATH")
    private String posterPath;

    @Column("BACKDROP_PATH")
    private String backdropPath;

    @Column("VOTE_AVERAGE")
    private Double voteAverage;

    @Column("VOTE_COUNT")
    private Integer voteCount;

    @Column("POPULARITY")
    private Double popularity;

}
