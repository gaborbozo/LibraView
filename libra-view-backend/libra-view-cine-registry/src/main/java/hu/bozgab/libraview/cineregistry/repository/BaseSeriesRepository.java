package hu.bozgab.libraview.cineregistry.repository;

import java.util.Collection;

import hu.bozgab.libraview.cineregistry.domain.Series;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@NoRepositoryBean
public interface BaseSeriesRepository extends ReactiveCrudRepository<Series, Long> {

    @Query("""
                SELECT
                    c.*
                FROM cinematic c
                LEFT JOIN user_cinematic_reference ucr 
                    ON c.reference_id = ucr.cinematic_reference_id
                WHERE
                    c.discriminator = :type AND
                    (:userId IS NULL OR ucr.user_id = :userId) AND
                    c.title LIKE CONCAT('%', :title, '%')
                LIMIT :limit
                OFFSET :offset
            """)
    Flux<Series> page(
            @Param("title") String title,
            @Param("userId") Long userId,
            @Param("limit") long limit,
            @Param("offset") long offset,
            @Param("discriminator") CinematicType discriminator
    );

    Mono<Boolean> existsByReferenceIdAndDiscriminator(Long referenceId, CinematicType discriminator);

    Mono<Series> findByReferenceIdAndDiscriminator(Long referenceId, CinematicType discriminator);

    Flux<Series> findAllByReferenceIdInAndDiscriminator(Collection<Long> referenceIds, CinematicType discriminator);

}
