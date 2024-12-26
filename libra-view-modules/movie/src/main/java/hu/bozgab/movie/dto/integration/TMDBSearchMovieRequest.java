package hu.bozgab.movie.dto.integration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TMDBSearchMovieRequest {

    private String query;

    @Builder.Default
    private Boolean includeAdult = true;

    @Builder.Default
    private String language = "en-US";

    @Builder.Default
    private Integer page = 1;

}
