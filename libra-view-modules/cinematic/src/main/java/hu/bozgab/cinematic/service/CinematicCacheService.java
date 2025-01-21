package hu.bozgab.cinematic.service;

import hu.bozgab.cinematic.dto.CinematicDTO;

import java.util.Optional;


public interface CinematicCacheService {

    void updateCinematic(CinematicDTO cinematicDTO);

    Optional<CinematicDTO> getCinematic(Long cinematicId);

}
