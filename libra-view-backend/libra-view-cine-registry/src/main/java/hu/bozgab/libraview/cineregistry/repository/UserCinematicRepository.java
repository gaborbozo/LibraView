package hu.bozgab.libraview.cineregistry.repository;

import java.util.Collection;

import hu.bozgab.libraview.cineregistry.domain.UserCinematicReference;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface UserCinematicRepository extends ReactiveCrudRepository<UserCinematicReference, Long> {

    Flux<UserCinematicReference> findAllByUserIdAndCinematicReferenceIdInAndDiscriminator(Long userId, Collection<Long> referenceIds, CinematicType discriminator);

    @Modifying
    Mono<Void> deleteAllByUserIdAndCinematicReferenceIdInAndDiscriminator(Long userId, Collection<Long> referenceIds, CinematicType discriminator);


}
