package hu.bozgab.cinematic.dto.integration.movies;

import java.util.Date;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, exclude = {})
@ToString(callSuper = true, exclude = {})
public class TMDBMovieGeneral {

    private String backdrop_path;

    private Set<Integer> genre_ids;

    private Long id;

    private String overview;

    private Double popularity;

    private String poster_path;

    private Date release_date;

    private String title;

    private Double vote_average;

    private Integer vote_count;

}
