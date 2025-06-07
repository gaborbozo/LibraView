package hu.bozgab.cinematic.service;

import java.util.List;
import java.util.Optional;

import hu.bozgab.cinematic.dto.CinematicDTO;
import hu.bozgab.cinematic.dto.GenreDTO;
import hu.bozgab.cinematic.dto.client.CinematicRequest;
import hu.bozgab.cinematic.dto.client.GetCinematicResponse;


public interface CinematicService {

    List<GenreDTO> updateGenres();

    List<GenreDTO> availableGenres();

    void addCinematic(CinematicRequest request);

    Optional<CinematicDTO> getCinematic(CinematicRequest request);

    GetCinematicResponse getCinematics();

}
