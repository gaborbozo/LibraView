package hu.bozgab.movie.dto.integration.configuration;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TMDBDetailsDTO {

    private TMDBImageDetailsDTO images;

    private List<String> change_keys = new ArrayList<>();

}
