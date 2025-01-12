package hu.bozgab.movie.dto.integration.movies;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TMDBSearchMovieResponse {

    private int page;

    private List<TMDBMovieDetailsDTO> results;

    private int total_pages;

    private int total_results;

}
