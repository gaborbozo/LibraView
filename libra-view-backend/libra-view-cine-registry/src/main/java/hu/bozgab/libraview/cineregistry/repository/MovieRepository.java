package hu.bozgab.libraview.cineregistry.repository;

import java.util.Collection;

import hu.bozgab.libraview.cineregistry.domain.Movie;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface MovieRepository extends BaseMovieRepository {

    default Mono<Boolean> existsByReferenceId(Long referenceId) {
        return existsByReferenceIdAndDiscriminator(referenceId, CinematicType.MOVIE);
    }

    default Mono<Movie> findByReferenceId(Long referenceId) {
        return findByReferenceIdAndDiscriminator(referenceId, CinematicType.MOVIE);
    }

    default Flux<Movie> findAllByReferenceIdIn(Collection<Long> referenceIds) {
        return findAllByReferenceIdInAndDiscriminator(referenceIds, CinematicType.MOVIE);
    }

}
