package hu.bozgab.libraviewtmdb.repository;

import hu.bozgab.libraviewtmdb.domain.Series;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface SeriesRepository extends ReactiveCrudRepository<Series, Long> {

    Mono<Boolean> existsByTmdbId(Long tmdbId);

}
