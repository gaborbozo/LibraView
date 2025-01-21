package hu.bozgab.cinematic.repository;

import hu.bozgab.cinematic.domain.Series;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SeriesRepository extends JpaRepository<Series, Long> {

}
