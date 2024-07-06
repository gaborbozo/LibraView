package hu.bozgab.movie.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "movie")
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

}
