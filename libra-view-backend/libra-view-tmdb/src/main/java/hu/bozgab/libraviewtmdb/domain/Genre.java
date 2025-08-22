package hu.bozgab.libraviewtmdb.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Getter
@Setter
@Table("GENRE")
public class Genre {

    @Id
    @Column("ID")
    private Long id;

    @Column("TMDB_ID")
    private Long tmdbId;

    @Column("NAME")
    private String name;

}