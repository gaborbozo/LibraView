package hu.bozgab.movie.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "discriminator"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MovieDTO.class, name = "MOVIE"),
        @JsonSubTypes.Type(value = SeriesDTO.class, name = "SERIES")
})
public class CinematicDTO {

    private Long id;

    private Long tmdbId;

    private String title;

    @Builder.Default
    private Set<Long> genreIds = new HashSet<>();

}
