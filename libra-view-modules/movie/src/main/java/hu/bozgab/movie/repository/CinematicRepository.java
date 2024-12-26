package hu.bozgab.movie.repository;

import hu.bozgab.movie.domain.Cinematic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CinematicRepository extends JpaRepository<Cinematic, Long> {

}
