package hu.bozgab.cinematic.dto.integration.search;

import hu.bozgab.cinematic.dto.integration.core.TMDBGeneralResponse;
import hu.bozgab.cinematic.dto.integration.movies.TMDBMovieDetailsDTO;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, exclude = {})
@ToString(callSuper = true, exclude = {})
public class TMDBSearchMovieResponse extends TMDBGeneralResponse {

    private int page;

    @Builder.Default
    private List<TMDBMovieDetailsDTO> results = new ArrayList<>();

    private int total_pages;

    private int total_results;

}
