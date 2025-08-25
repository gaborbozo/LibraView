package hu.bozgab.libraview.cineregistry.repository;

import hu.bozgab.libraview.cineregistry.domain.Genre;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends ReactiveCrudRepository<Genre, Long> {

}
