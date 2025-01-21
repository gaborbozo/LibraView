package hu.bozgab.cinematic.service.impl;

import hu.bozgab.cinematic.domain.Cinematic;
import hu.bozgab.cinematic.domain.Genre;
import hu.bozgab.cinematic.dto.CinematicDTO;
import hu.bozgab.cinematic.dto.GenreDTO;
import hu.bozgab.cinematic.dto.IdRequest;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGenreDTO;
import hu.bozgab.cinematic.mapper.CinematicMapper;
import hu.bozgab.cinematic.mapper.GenreMapper;
import hu.bozgab.cinematic.mapper.TMDBMapper;
import hu.bozgab.cinematic.repository.CinematicRepository;
import hu.bozgab.cinematic.repository.GenreRepository;
import hu.bozgab.cinematic.service.CinematicCacheService;
import hu.bozgab.cinematic.service.CinematicService;
import hu.bozgab.cinematic.service.TMDBService;
import hu.bozgab.cinematic.shared.CacheEntry;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Log4j2
@RequiredArgsConstructor
@Service
public class CinematicServiceImpl implements CinematicService, CinematicCacheService {

    private final TMDBService tmdbService;

    private final CinematicRepository cinematicRepository;
    private final GenreRepository genreRepository;

    private final CinematicMapper cinematicMapper;
    private final GenreMapper genreMapper;
    private final TMDBMapper tmdbMapper;

    private final Map<Long, CacheEntry<CinematicDTO>> cachedCinematics = new ConcurrentHashMap<>();

    private final int EXPIRATION_TIME = 5 * 60 * 1000;


    @Override
    public List<GenreDTO> updateGenres() {
        List<TMDBGenreDTO> tmdbGenreDTOS = tmdbService.getGenres();
        List<Genre> genreEntities = genreRepository.findAll();

        tmdbMapper.toGenreEntitiesForPersist(genreEntities, tmdbGenreDTOS);
        genreEntities = genreRepository.saveAll(genreEntities);

        return genreMapper.toGenreDTOS(genreEntities);
    }

    @Override
    public List<GenreDTO> availableGenres() {
        return genreMapper.toGenreDTOS(genreRepository.findAll());
    }

    @Override
    public void addCinematic(IdRequest request) {
        if(cachedCinematics.containsKey(request.getId())) {
            if(!cinematicRepository.existsByTmdbId(request.getId())) {
                Cinematic cinematic = cinematicMapper
                        .toCinematicEntityForPersist(null, cachedCinematics.get(request.getId()).getValue());

                cinematicRepository.save(cinematic);
            }
        }
    }

    @Override
    public void updateCinematic(CinematicDTO cinematicDTO) {
        cachedCinematics.put(cinematicDTO.getId(), new CacheEntry<CinematicDTO>(cinematicDTO, EXPIRATION_TIME));
    }

    @Override
    public Optional<CinematicDTO> getCinematic(Long cinematicId) {
        CacheEntry<CinematicDTO> cinematicDTO = cachedCinematics
                .computeIfPresent(cinematicId, (_, dto) -> dto.isExpired() ? null : dto);

        return cinematicDTO != null ? Optional.of(cinematicDTO.getValue()) : Optional.empty();
    }

    @Scheduled(fixedRate = 60_000)
    private void evictExpiredCinematics() {
        log.info("evictExpiredCinematics: " + cachedCinematics.keySet());
        cachedCinematics.entrySet()
                .removeIf(entry -> entry.getValue().isExpired());
        log.info("evictExpiredCinematics: " + cachedCinematics.keySet());
    }

}
