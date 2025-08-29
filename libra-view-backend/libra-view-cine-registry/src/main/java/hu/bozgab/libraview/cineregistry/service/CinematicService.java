package hu.bozgab.libraview.cineregistry.service;

import java.util.List;

import hu.bozgab.libraview.cineregistry.domain.Cinematic;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicDTO;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import hu.bozgab.libraview.cineregistry.generated.model.MovieDetails200Response;
import hu.bozgab.libraview.cineregistry.generated.model.TvSeriesDetails200Response;
import hu.bozgab.libraview.cineregistry.mapper.GenreMapper;
import hu.bozgab.libraview.cineregistry.mapper.MovieMapper;
import hu.bozgab.libraview.cineregistry.mapper.SeriesMapper;
import hu.bozgab.libraview.cineregistry.repository.CinematicGenreRepository;
import hu.bozgab.libraview.cineregistry.repository.GenreRepository;
import hu.bozgab.libraview.cineregistry.repository.MovieRepository;
import hu.bozgab.libraview.cineregistry.repository.SeriesRepository;
import hu.bozgab.libraview.cineregistry.tmdb.TMDBClient;
import hu.bozgab.libraview.cineregistry.util.TMDBLanguage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
@Service
public class CinematicService {

    private final GenreRepository genreRepository;
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;
    private final CinematicGenreRepository cinematicGenreRepository;

    private final MovieMapper movieMapper;
    private final SeriesMapper seriesMapper;

    private final TMDBClient tmdbClient;
    private final GenreMapper genreMapper;

    public Mono<CinematicDTO> getCinematic(Long referenceId, CinematicType type) {
        return switch(type) {
            case MOVIE -> movieRepository.findByReferenceId(referenceId).flatMap(movieEntity ->
                    cinematicGenreRepository.findAllByCinematicId(movieEntity.getId()).collectList().flatMap(cinematicGenreEntities ->
                            Mono.just(movieMapper.toMovieDto(movieEntity, cinematicGenreEntities))
                    )
            );
            case SERIES -> seriesRepository.findByReferenceId(referenceId).flatMap(seriesEntity ->
                    cinematicGenreRepository.findAllByCinematicId(seriesEntity.getId()).collectList().flatMap(cinematicGenreEntities ->
                            Mono.just(seriesMapper.toSeriesDto(seriesEntity, cinematicGenreEntities))
                    )
            );
        };
    }

    public Mono<Void> storeCinematic(Long referenceId, CinematicType type) {
        return switch(type) {
            case MOVIE -> movieRepository.existsByReferenceId(referenceId)
                    .flatMap(exists -> {
                        if(exists) {
                            log.debug("Movie already exists");
                            return Mono.empty();
                        }
                        log.debug("Movie not yet exists, starting storing process");

                        MovieDetails200Response movie = tmdbClient.movieDetails(referenceId.intValue(), "", TMDBLanguage.EN.getCode());
                        return movieRepository.save(movieMapper.toMovieEntity(movie)).flatMap(movieEntity ->
                                storeGenres(movieEntity, movie.getGenres().stream().map(g -> g.getId().longValue()).toList())
                        );
                    });
            case SERIES -> seriesRepository.existsByReferenceId(referenceId)
                    .flatMap(exists -> {
                        if(exists) {
                            log.debug("Series already exists");
                            return Mono.empty();
                        }
                        log.debug("Series not yet exists, starting storing process");

                        TvSeriesDetails200Response series = tmdbClient.tvSeriesDetails(referenceId.intValue(), "", TMDBLanguage.EN.getCode());
                        return seriesRepository.save(seriesMapper.toSeriesEntity(series)).flatMap(seriesEntity ->
                                storeGenres(seriesEntity, series.getGenres().stream().map(g -> g.getId().longValue()).toList())
                        );
                    });
        };
    }

    private Mono<Void> storeGenres(Cinematic cinematicEntity, List<Long> genreIds) {
        return genreRepository
                .findAllByReferenceIdIsIn(genreIds)
                .collectList()
                .flatMap(genreEntities ->
                        cinematicGenreRepository.saveAll(
                                genreEntities.stream().map(genreEntity ->
                                        genreMapper.toCinematicGenreEntity(cinematicEntity, genreEntity)
                                ).toList()
                        ).then()
                );
    }

}
