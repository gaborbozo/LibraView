package hu.bozgab.cinematic.repository;

import java.util.List;

import hu.bozgab.cinematic.domain.UserCinematic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserCinematicRepository extends JpaRepository<UserCinematic, Long> {

    boolean existsByUserIdAndCinematicId(Long userId, Long cinematicId);

    List<UserCinematic> findAllByUserId(Long userId, Pageable pageable);

}