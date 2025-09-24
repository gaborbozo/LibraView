package hu.bozgab.libraview.cineregistry.service;

import java.util.List;

import hu.bozgab.libraview.cineregistry.domain.UserCinematicReference;
import hu.bozgab.libraview.cineregistry.generated.model.CinematicType;
import hu.bozgab.libraview.cineregistry.repository.UserCinematicRepository;
import hu.bozgab.libraview.common.authentication.LibraUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserCinematicService {

    private final UserCinematicRepository userCinematicRepository;

    public Flux<Long> checkCinematicInUserLibrary(LibraUser user, List<Long> referenceIds, CinematicType type) {
        return userCinematicRepository.findAllByUserIdAndCinematicReferenceIdInAndDiscriminator(user.getId(), referenceIds, type)
                .map(UserCinematicReference::getCinematicReferenceId);
    }

    public Mono<Void> addCinematicToUserLibrary(LibraUser user, List<Long> referenceIds, CinematicType type) {
        return userCinematicRepository.findAllByUserIdAndCinematicReferenceIdInAndDiscriminator(user.getId(), referenceIds, type)
                .collectList()
                .flatMap(persistedReferenceIds -> {
                    referenceIds.removeAll(persistedReferenceIds.stream().map(UserCinematicReference::getCinematicReferenceId).toList());

// NOTE: Used to check if the referenced id is persisted to the database already
//
//                    Function<List<Long>, Flux<? extends Cinematic>> finder = switch(type) {
//                        case MOVIE -> movieRepository::findAllByReferenceIdIn;
//                        case SERIES -> seriesRepository::findAllByReferenceIdIn;
//                    };
//
//                    return finder.apply(referenceIds)
//                            .collectList()
//                            .flatMap(entities -> {
//                                var entityMap = entities.stream().collect(Collectors.toMap(Cinematic::getReferenceId, Function.identity()));
//
//                                return userCinematicRepository.saveAll(
//                                        referenceIds.stream().map(referenceId -> {
//                                            UserCinematicReference userCinematicReference = new UserCinematicReference();
//                                            userCinematicReference.setUserId(user.getId());
//                                            userCinematicReference.setCinematicReferenceId(entityMap.get(referenceId).getReferenceId());
//                                            userCinematicReference.setDiscriminator(entityMap.get(referenceId).getDiscriminator());
//                                            return userCinematicReference;
//                                        }).toList()).then();
//                            });
                    return userCinematicRepository.saveAll(
                            referenceIds.stream().map(referenceId -> {
                                UserCinematicReference userCinematicReference = new UserCinematicReference();
                                userCinematicReference.setUserId(user.getId());
                                userCinematicReference.setCinematicReferenceId(referenceId);
                                userCinematicReference.setDiscriminator(type);
                                return userCinematicReference;
                            }).toList()
                    ).then();
                });
    }

    public Mono<Void> deleteCinematicFromUserLibrary(LibraUser user, List<Long> referenceIds, CinematicType type) {
        return userCinematicRepository.deleteAllByUserIdAndCinematicReferenceIdInAndDiscriminator(user.getId(), referenceIds, type)
                .then();
    }

}
