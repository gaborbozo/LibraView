package hu.bozgab.movie.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "movie")
@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    private Long tMDBId;

    private String name;

}