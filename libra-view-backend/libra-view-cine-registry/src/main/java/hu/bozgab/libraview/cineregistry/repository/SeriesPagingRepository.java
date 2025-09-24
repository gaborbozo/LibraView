package hu.bozgab.libraview.cineregistry.repository;

import hu.bozgab.libraview.cineregistry.domain.Movie;
import hu.bozgab.libraview.cineregistry.domain.Series;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;


public interface SeriesPagingRepository extends ReactiveSortingRepository<Series, Long> {

    Flux<Movie> findAllByTitleContainingIgnoreCase(Pageable pagedRequest, String title);

}
