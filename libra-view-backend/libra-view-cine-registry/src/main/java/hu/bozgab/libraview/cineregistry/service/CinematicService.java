package hu.bozgab.libraview.cineregistry.service;

import hu.bozgab.libraview.cineregistry.generated.model.CinematicDTO;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import hu.bozgab.libraview.cineregistry.mapper.MovieMapper;
import hu.bozgab.libraview.cineregistry.mapper.SeriesMapper;
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

    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;

    private final MovieMapper movieMapper;
    private final SeriesMapper seriesMapper;

    private final TMDBClient tmdbClient;

    public Mono<CinematicDTO> getCinematic(Long referenceId, CinematicType type) {
        switch(type) {
            case MOVIE -> {
                return movieRepository.findByReferenceId(referenceId).map(movieMapper::toMovieDto);
            }
            case SERIES -> {
                return Mono.empty();
            }
            case null -> {
                return Mono.empty();
            }
        }
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
                        return movieRepository.save(
                                movieMapper.toMovieEntity(
                                        tmdbClient.movieDetails(referenceId.intValue(), "", TMDBLanguage.EN.getCode())
                                )
                        );
                    }).then();
            case SERIES -> seriesRepository.existsByReferenceId(referenceId)
                    .flatMap(exists -> {
                        if(exists) {
                            log.debug("Series already exists");
                            return Mono.empty();
                        }
                        log.debug("Series not yet exists, starting storing process");
                        return seriesRepository.save(
                                seriesMapper.toSeriesEntity(
                                        tmdbClient.tvSeriesDetails(referenceId.intValue(), "", TMDBLanguage.EN.getCode()))
                        );
                    }).then();
        };
    }

}
