package hu.bozgab.cinematic.service;

import hu.bozgab.cinematic.dto.GenreDTO;
import hu.bozgab.cinematic.dto.IdRequest;

import java.util.List;


public interface CinematicService {

    List<GenreDTO> updateGenres();

    List<GenreDTO> availableGenres();

    void addCinematic(IdRequest request);

}
