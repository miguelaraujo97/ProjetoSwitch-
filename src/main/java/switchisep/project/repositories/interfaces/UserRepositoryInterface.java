package switchisep.project.repositories.interfaces;

import org.springframework.stereotype.Service;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.Email;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.UserID;

import java.util.List;
import java.util.Optional;

@Service
public interface UserRepositoryInterface {
    List<User> findAll();

    User save(User user);

    Optional<User> findByEmail(Email email);

    Optional<User> findById(UserID id);

    void deleteAll();

    List<User> findByProfileID(ProfileID profileID);


}
