package hu.bozgab.libra_view.authentication.repository;

import hu.bozgab.libra_view.authentication.domain.LibraUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LibraUserRepository extends JpaRepository<LibraUser, Long> {

    Optional<LibraUser> findByUsername(String username);

}
