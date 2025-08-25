package hu.bozgab.libraview.cineregistry.repository;

import hu.bozgab.libraview.cineregistry.domain.Movie;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface MovieRepository extends ReactiveCrudRepository<Movie, Long> {

    Mono<Boolean> existsByReferenceId(Long referenceId);

    Mono<Movie> findByReferenceId(Long referenceId);

}
