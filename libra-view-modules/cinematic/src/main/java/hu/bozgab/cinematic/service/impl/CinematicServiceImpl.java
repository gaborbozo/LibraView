package hu.bozgab.cinematic.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import hu.bozgab.cinematic.domain.Cinematic;
import hu.bozgab.cinematic.domain.Genre;
import hu.bozgab.cinematic.domain.UserCinematic;
import hu.bozgab.cinematic.dto.CinematicDTO;
import hu.bozgab.cinematic.dto.GenreDTO;
import hu.bozgab.cinematic.dto.client.CinematicRequest;
import hu.bozgab.cinematic.dto.client.GetCinematicResponse;
import hu.bozgab.cinematic.dto.enums.CinematicType;
import hu.bozgab.cinematic.dto.integration.genres.TMDBGenre;
import hu.bozgab.cinematic.exception.CinematicNotFound;
import hu.bozgab.cinematic.mapper.CinematicMapper;
import hu.bozgab.cinematic.mapper.GenreMapper;
import hu.bozgab.cinematic.mapper.TMDBGenreMapper;
import hu.bozgab.cinematic.mapper.TMDBMovieMapper;
import hu.bozgab.cinematic.repository.CinematicRepository;
import hu.bozgab.cinematic.repository.GenreRepository;
import hu.bozgab.cinematic.repository.MovieRepository;
import hu.bozgab.cinematic.repository.UserCinematicRepository;
import hu.bozgab.cinematic.service.CinematicService;
import hu.bozgab.cinematic.service.TMDBService;
import hu.bozgab.shared.authentication.repository.LibraUserRepository;
import hu.bozgab.shared.authentication.service.LibraUserContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Transactional
@Log4j2
@RequiredArgsConstructor
@Service
public class CinematicServiceImpl implements CinematicService {

    private final TMDBService tmdbService;
    private final LibraUserContext libraUserContext;

    private final CinematicRepository cinematicRepository;
    private final UserCinematicRepository userCinematicRepository;
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final LibraUserRepository libraUserRepository;

    private final CinematicMapper cinematicMapper;
    private final GenreMapper genreMapper;
    private final TMDBMovieMapper tmdbMovieMapper;
    private final TMDBGenreMapper tmdbGenreMapper;


    @Override
    public List<GenreDTO> updateGenres() {
        List<TMDBGenre> tmdbGenres = tmdbService.getGenres();
        List<Genre> genreEntities = genreRepository.findAll();

        tmdbGenreMapper.toGenreEntitiesForPersist(genreEntities, tmdbGenres);
        genreEntities = genreRepository.saveAll(genreEntities);

        return genreMapper.toGenreDTOS(genreEntities);
    }

    @Override
    public List<GenreDTO> availableGenres() {
        return genreMapper.toGenreDTOS(genreRepository.findAll());
    }

    @Override
    public void addCinematic(CinematicRequest request) {
        Optional<CinematicDTO> optCinematicDTO = getCinematic(request);
        if(optCinematicDTO.isEmpty()) {
            optCinematicDTO = Optional.ofNullable(
                    tmdbMovieMapper.toMovieDTO(
                            tmdbService.getMovieDetails(request.getId())
                    )
            );
        }
        optCinematicDTO.ifPresentOrElse(cinematicDTO -> {
                    Long userId = libraUserContext.getCurrentUser().getId();
                    Long cinematicId = cinematicDTO.getId();
                    if(cinematicId == null) {
                        cinematicId = cinematicRepository.save(cinematicMapper
                                .toCinematicEntityForPersist(null, cinematicDTO)
                        ).getId();
                    }

                    if(!userCinematicRepository.existsByUserIdAndCinematicId(userId, cinematicId)) {
                        UserCinematic userCinematic = cinematicMapper.createUserCinematicAssociationEntity(userId, cinematicId);
                        userCinematicRepository.save(userCinematic);
                    }
                },
                () -> {
                    throw new CinematicNotFound();
                });
    }

    @Override
    public Optional<CinematicDTO> getCinematic(CinematicRequest request) {
        Optional<? extends Cinematic> cinematic;

        switch(request.getCinematic()) {
            case CinematicType.MOVIE -> cinematic = movieRepository.findByTmdbId(request.getId());
            default -> throw new RuntimeException("Unsupported cinematic type");
        }

        return cinematic.map(cinematicMapper::toCinematicDTO);
    }

    @Override
    public GetCinematicResponse getCinematics() {
        List<UserCinematic> userCinematics = userCinematicRepository.findAllByUserId(libraUserContext.getCurrentUser().getId(), Pageable.ofSize(10));
        return GetCinematicResponse.builder()
                .cinematics(
                        cinematicMapper.toCinematicDTOS(
                                userCinematics.stream().map(UserCinematic::getCinematic).collect(Collectors.toList())
                        )
                ).build();
    }

}
