package hu.bozgab.cinematic.repository;

import hu.bozgab.cinematic.domain.Cinematic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CinematicRepository extends JpaRepository<Cinematic, Long> {

    boolean existsByTmdbId(Long tmdbId);

}
