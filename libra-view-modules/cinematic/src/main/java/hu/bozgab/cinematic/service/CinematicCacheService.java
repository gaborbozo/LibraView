package hu.bozgab.cinematic.service;

import java.util.Optional;

import hu.bozgab.cinematic.dto.CinematicDTO;
import hu.bozgab.cinematic.dto.CinematicRequest;


public interface CinematicCacheService {

    void updateCinematic(CinematicDTO cinematicDTO);

    Optional<CinematicDTO> getCinematic(CinematicRequest request);

}
