package hu.bozgab.libraview.cineregistry.repository;

import java.util.List;

import hu.bozgab.libraview.cineregistry.domain.Genre;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface GenreRepository extends ReactiveCrudRepository<Genre, Long> {
    
    Flux<Genre> findAllByReferenceIdIsIn(List<Long> referenceIds);

}
