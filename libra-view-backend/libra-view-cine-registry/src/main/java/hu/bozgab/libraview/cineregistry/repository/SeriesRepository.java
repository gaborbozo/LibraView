package hu.bozgab.libraview.cineregistry.repository;

import hu.bozgab.libraview.cineregistry.domain.Series;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public interface SeriesRepository extends ReactiveCrudRepository<Series, Long> {

    Mono<Boolean> existsByReferenceId(Long referenceId);

}
