package hu.bozgab.libraview.cineregistry.repository;

import hu.bozgab.libraview.cineregistry.domain.CinematicGenre;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface CinematicGenreRepository extends ReactiveCrudRepository<CinematicGenre, Long> {

    Flux<CinematicGenre> findAllByCinematicId(Long cinematicId);

}