package hu.bozgab.movie.repository;

import hu.bozgab.movie.domain.Genre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Query("SELECT g.tMDBId FROM Genre g")
    List<Long> findAllTMDBIds();

}
