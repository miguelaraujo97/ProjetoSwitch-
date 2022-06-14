package switchisep.project.domain.user;

import org.springframework.stereotype.Service;
import switchisep.project.domain.valueobjects.*;

@Service
public class UserFactory implements UserFactoryInterface {

    public User createNewUser(UserID userID, UserName name, Email email, Function function,
                              PasswordHash hashedPassword, Photo photoURL, ProfileID profileID) {
        return new User(userID, name, email, function, hashedPassword, photoURL, profileID);
    }
}
