package hu.bozgab.shared.authentication.repository;

import java.util.Optional;

import hu.bozgab.shared.authentication.domain.LibraUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LibraUserRepository extends JpaRepository<LibraUser, Long> {

    Optional<LibraUser> findByUsername(String username);

}
