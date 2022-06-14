package switchisep.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import switchisep.project.datamodel.ProfileJpa;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;

import java.util.List;
import java.util.Optional;

@Service
public interface ProfileJpaRepository extends JpaRepository<ProfileJpa, ProfileID> {

    boolean existsByName(Name name);

    Optional<ProfileJpa> findByName(Name profileName);

    Optional<ProfileJpa> findById(ProfileID profileID);

    List<ProfileJpa> findAll();

}

