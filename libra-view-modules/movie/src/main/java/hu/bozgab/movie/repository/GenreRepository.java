package hu.bozgab.movie.repository;

import hu.bozgab.movie.domain.Genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
