package hu.bozgab.libraviewtmdb.service;

import hu.bozgab.LibraViewTMDB.generated.model.CinematicDTO;
import hu.bozgab.LibraViewTMDB.generated.model.CinematicType;
import hu.bozgab.libraviewtmdb.mapper.MovieMapper;
import hu.bozgab.libraviewtmdb.mapper.SeriesMapper;
import hu.bozgab.libraviewtmdb.repository.MovieRepository;
import hu.bozgab.libraviewtmdb.repository.SeriesRepository;
import hu.bozgab.libraviewtmdb.tmdb.TMDBClient;
import hu.bozgab.libraviewtmdb.util.TMDBLanguage;
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

    public Mono<CinematicDTO> getCinematic(Long tmdbId, CinematicType type) {
        switch(type) {
            case MOVIE -> {
                return movieRepository.findByTmdbId(tmdbId).map(movieMapper::toMovieDto);
            }
            case SERIES -> {
                return Mono.empty();
            }
            case null -> {
                return Mono.empty();
            }
        }
    }

    public Mono<Void> storeCinematic(Long tmdbId, CinematicType type) {
        return switch(type) {
            case MOVIE -> movieRepository.existsByTmdbId(tmdbId)
                    .flatMap(exists -> {
                        if(exists) {
                            log.debug("Movie already exists");
                            return Mono.empty();
                        }
                        log.debug("Movie not yet exists, starting storing process");
                        return movieRepository.save(
                                movieMapper.toMovieEntity(
                                        tmdbClient.movieDetails(tmdbId.intValue(), "", TMDBLanguage.EN.getCode())
                                )
                        );
                    }).then();
            case SERIES -> seriesRepository.existsByTmdbId(tmdbId)
                    .flatMap(exists -> {
                        if(exists) {
                            log.debug("Series already exists");
                            return Mono.empty();
                        }
                        log.debug("Series not yet exists, starting storing process");
                        return seriesRepository.save(
                                seriesMapper.toSeriesEntity(
                                        tmdbClient.tvSeriesDetails(tmdbId.intValue(), "", TMDBLanguage.EN.getCode()))
                        );
                    }).then();
        };
    }

}
