package hu.bozgab.movie.dto.integration.configuration;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TMDBImageDetailsDTO {

    private String base_url;

    private String secure_base_url;

    List<String> backdrop_sizes = new ArrayList<>();

    List<String> logo_sizes = new ArrayList<>();

    List<String> poster_sizes = new ArrayList<>();

    List<String> profile_sizes = new ArrayList<>();

    List<String> still_sizes = new ArrayList<>();

}
