package hu.bozgab.cinematic.dto.integration.search;

import java.util.ArrayList;
import java.util.List;

import hu.bozgab.cinematic.dto.integration.core.TMDBGeneralResponse;
import hu.bozgab.cinematic.dto.integration.core.TMDBPageableResponse;
import hu.bozgab.cinematic.dto.integration.movies.TMDBMovieGeneral;
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
@EqualsAndHashCode(callSuper = true, exclude = {})
@ToString(callSuper = true, exclude = {})
public class TMDBSearchMovieResponse extends TMDBSearchResponse implements TMDBGeneralResponse, TMDBPageableResponse {

    @Builder.Default
    private String discriminator = "MOVIE";

    private Integer page;

    @Builder.Default
    private List<TMDBMovieGeneral> results = new ArrayList<>();

    private Integer total_pages;

    private Integer total_results;

}
