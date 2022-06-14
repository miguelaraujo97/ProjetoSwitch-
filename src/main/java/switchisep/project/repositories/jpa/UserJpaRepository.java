package switchisep.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import switchisep.project.datamodel.UserJpa;
import switchisep.project.domain.valueobjects.Email;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.UserID;

import java.util.List;
import java.util.Optional;

@Service
public interface UserJpaRepository extends JpaRepository<UserJpa, UserID> {

    Optional<UserJpa> findByEmail(Email email);
    List<UserJpa> findByProfileID(ProfileID profileID);

}
