package hu.bozgab.movie.dto.integration;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class TMDBFindGenresResponse {

    private List<TMDBGenreDTO> genres = new ArrayList<>();

}
