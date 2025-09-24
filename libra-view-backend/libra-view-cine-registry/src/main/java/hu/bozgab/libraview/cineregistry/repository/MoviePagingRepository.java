package hu.bozgab.libraview.cineregistry.repository;

import hu.bozgab.libraview.cineregistry.domain.Movie;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface MoviePagingRepository extends ReactiveSortingRepository<Movie, Long> {

    Flux<Movie> findAllByTitleContainingIgnoreCase(Pageable pagedRequest, String title);

}
