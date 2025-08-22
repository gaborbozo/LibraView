package hu.bozgab.libraviewtmdb.repository;

import hu.bozgab.libraviewtmdb.domain.Genre;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends ReactiveCrudRepository<Genre, Long> {

}
