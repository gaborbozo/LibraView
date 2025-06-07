package hu.bozgab.cinematic.dto.integration.genres;

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
public class TMDBGenre {

    private Long id;

    private String name;

}
