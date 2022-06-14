package switchisep.project.domain.user;

import org.springframework.stereotype.Service;
import switchisep.project.domain.valueobjects.*;

@Service
public interface UserFactoryInterface {

    User createNewUser(UserID userID, UserName name, Email email, Function function,
                       PasswordHash hashedPassword, Photo photoURL, ProfileID profileID);
}
