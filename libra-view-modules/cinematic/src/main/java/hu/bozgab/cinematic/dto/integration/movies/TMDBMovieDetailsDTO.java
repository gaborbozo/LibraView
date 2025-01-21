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
public class TMDBMovieDetailsDTO {

    private Boolean adult;

    private String backdrop_path;

    private Long id;

    private String title;

    private String original_language;

    private String original_title;

    private String overview;

    private String poster_path;

    private String media_type;

    private Set<Integer> genre_ids;

    private Double popularity;

    private Date release_date;

    private Boolean video;

    private Double vote_average;

    private Integer vote_count;

}
