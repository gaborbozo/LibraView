package hu.bozgab.libraview.cineregistry.repository;

import java.util.Collection;

import hu.bozgab.libraview.cineregistry.domain.Series;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@NoRepositoryBean
public interface BaseSeriesRepository extends ReactiveCrudRepository<Series, Long> {

    Mono<Boolean> existsByReferenceIdAndDiscriminator(Long referenceId, CinematicType discriminator);

    Mono<Series> findByReferenceIdAndDiscriminator(Long referenceId, CinematicType discriminator);

    Flux<Series> findAllByReferenceIdInAndDiscriminator(Collection<Long> referenceIds, CinematicType discriminator);

}
