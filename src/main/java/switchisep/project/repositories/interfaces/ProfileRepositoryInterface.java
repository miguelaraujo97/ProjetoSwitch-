package switchisep.project.repositories.interfaces;

import org.springframework.stereotype.Service;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileID;

import java.util.List;
import java.util.Optional;

@Service
public interface ProfileRepositoryInterface  {

    Profile save(Profile profile);

    Optional<Profile> findByName(Name profileName);

    boolean existsByName(Name profileName);

    List<Profile> getAllProfiles();

    void deleteAll();

    Optional<Profile> findById(ProfileID profileID);
}
