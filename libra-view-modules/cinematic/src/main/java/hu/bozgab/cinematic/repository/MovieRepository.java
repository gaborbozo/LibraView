package hu.bozgab.cinematic.repository;

import java.util.Optional;

import hu.bozgab.cinematic.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    boolean existsByTmdbId(Long tmdbId);

    Optional<Movie> findByTmdbId(Long tmdbId);

}
