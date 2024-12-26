package hu.bozgab.movie.repository;

import hu.bozgab.movie.domain.Series;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {

}
