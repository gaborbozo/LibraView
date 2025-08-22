package hu.bozgab.libraviewtmdb.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Table("CINEMATIC_GENRE")
public class CinematicGenre {

    @Column("CINEMATIC_ID")
    private Long cinematicId;

    @Column("GENRE_ID")
    private Long genreId;

}