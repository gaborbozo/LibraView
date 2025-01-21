package hu.bozgab.cinematic.dto.integration.search;

import hu.bozgab.cinematic.dto.integration.core.TMDBGeneralRequest;

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
public class TMDBSearchMovieRequest extends TMDBGeneralRequest {

    private String query;

    @Builder.Default
    private Boolean includeAdult = true;

    @Builder.Default
    private String language = "en-US";

    @Builder.Default
    private Integer page = 1;

}
