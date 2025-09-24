package hu.bozgab.libraview.cineregistry.repository;

import java.util.Collection;

import hu.bozgab.libraview.cineregistry.domain.Movie;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@NoRepositoryBean
public interface BaseMovieRepository extends ReactiveCrudRepository<Movie, Long> {

    Mono<Boolean> existsByReferenceIdAndDiscriminator(Long referenceId, CinematicType discriminator);

    Mono<Movie> findByReferenceIdAndDiscriminator(Long referenceId, CinematicType discriminator);

    Flux<Movie> findAllByReferenceIdInAndDiscriminator(Collection<Long> referenceIds, CinematicType discriminator);

}
