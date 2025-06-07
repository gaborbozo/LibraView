package hu.bozgab.cinematic.dto.integration.movies;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hu.bozgab.cinematic.dto.integration.core.TMDBGeneralResponse;
import hu.bozgab.cinematic.dto.integration.general.TMDBProductionCompany;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGenre;
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
public class TMDBMovieDetails implements TMDBGeneralResponse {

    private String backdrop_path;

    private Integer budget;

    private List<TMDBGenre> genres = new ArrayList<>();

    private String homepage;

    private String id;

    private String imdb_id;

    private String overview;

    private Integer popularity;

    private String poster_path;

    private List<TMDBProductionCompany> production_companies = new ArrayList<>();

    private Date release_date;

    private Integer revenue;

    private Integer runtime;

    private String status;

    private String title;

    private Integer vote_average;

    private Integer vote_count;

}
