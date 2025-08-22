package hu.bozgab.libraviewtmdb.repository;

import hu.bozgab.libraviewtmdb.domain.Movie;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface MovieRepository extends ReactiveCrudRepository<Movie, Long> {

    Mono<Boolean> existsByTmdbId(Long tmdbId);

    Mono<Movie> findByTmdbId(Long tmdbId);

}
