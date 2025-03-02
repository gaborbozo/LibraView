package hu.bozgab.cinematic.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import hu.bozgab.cinematic.domain.Cinematic;
import hu.bozgab.cinematic.domain.Genre;
import hu.bozgab.cinematic.dto.CinematicDTO;
import hu.bozgab.cinematic.dto.CinematicRequest;
import hu.bozgab.cinematic.dto.GenreDTO;
import hu.bozgab.cinematic.dto.enums.CinematicType;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGenreDTO;
import hu.bozgab.cinematic.exception.CinematicNotFound;
import hu.bozgab.cinematic.mapper.CinematicMapper;
import hu.bozgab.cinematic.mapper.GenreMapper;
import hu.bozgab.cinematic.mapper.TMDBMapper;
import hu.bozgab.cinematic.repository.CinematicRepository;
import hu.bozgab.cinematic.repository.GenreRepository;
import hu.bozgab.cinematic.repository.MovieRepository;
import hu.bozgab.cinematic.service.CinematicCacheService;
import hu.bozgab.cinematic.service.CinematicService;
import hu.bozgab.cinematic.service.TMDBService;
import hu.bozgab.shared.authentication.dto.LibraUserDTO;
import hu.bozgab.shared.authentication.repository.LibraUserRepository;
import hu.bozgab.shared.authentication.service.LibraUserContext;
import hu.bozgab.shared.cache.dto.CacheEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Log4j2
@RequiredArgsConstructor
@Service
public class CinematicServiceImpl implements CinematicService, CinematicCacheService {

    private final TMDBService tmdbService;
    private final LibraUserContext libraUserContext;

    private final CinematicRepository cinematicRepository;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final LibraUserRepository libraUserRepository;

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
    public void addCinematic(CinematicRequest request) {
        Optional<CinematicDTO> cinematicDTO = getCinematic(request);

        // This cache should only be used while tracking Movies; it must be deleted or refactored afterward.
        if(cinematicDTO.isEmpty() && cachedCinematics.containsKey(request.getId())) {
            cinematicDTO = Optional.of(cachedCinematics.get(request.getId()).getValue());
        }
        if(cinematicDTO.isEmpty()) {
            throw new CinematicNotFound();
        }

        LibraUserDTO user = libraUserContext.getCurrentUser();

        Long id;
        if(!cinematicRepository.existsByTmdbId(request.getId())) {
            Cinematic cinematic = cinematicMapper
                    .toCinematicEntityForPersist(null, cachedCinematics.get(request.getId()).getValue());

            cinematic = cinematicRepository.save(cinematic);
            id = cinematic.getId();
        } else {
            id = cinematicDTO.get().getId();
        }

        cinematicRepository.findById(id).ifPresent(cinematic -> {
            cinematic.getUsers().add(libraUserRepository.findById(user.getId()).orElseThrow(CinematicNotFound::new));
            cinematicRepository.save(cinematic);
        });
    }

    @Override
    public void updateCinematic(CinematicDTO cinematicDTO) {
        cachedCinematics.put(cinematicDTO.getTmdbId(), new CacheEntry<CinematicDTO>(cinematicDTO, EXPIRATION_TIME));
    }

    @Override
    public Optional<CinematicDTO> getCinematic(CinematicRequest request) {
        Optional<? extends Cinematic> cinematic;

        switch(request.getCinematic()) {
            case CinematicType.MOVIE -> cinematic = movieRepository.findByTmdbId(request.getId());
            default -> throw new CinematicNotFound();
        }

        return cinematic.map(cinematicMapper::toCinematicDTO);
    }

    @Scheduled(fixedRate = 60_000)
    private void evictExpiredCinematics() {
        log.info("evictExpiredCinematics: " + cachedCinematics.keySet());
        cachedCinematics.entrySet()
                .removeIf(entry -> entry.getValue().isExpired());
        log.info("evictExpiredCinematics: " + cachedCinematics.keySet());
    }

}
