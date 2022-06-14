package switchisep.project.datamodel.assemblers;


import org.springframework.stereotype.Service;
import switchisep.project.datamodel.UserJpa;
import switchisep.project.domain.user.*;

@Service
public class UserDomainDataAssembler {

    public UserJpa toData(User user) {
        return new UserJpa(user.getUserID(),
                user.getUserName(), user.getEmail(),
                user.getFunction(), user.getHashedPassword(),
                user.getPhoto(), user.getProfileID(), user.getUserStatus());
    }

    public User toDomain(UserJpa userJpa) {
        return new User(userJpa.getId(), userJpa.getUserName(),
                userJpa.getEmail(),userJpa.getFunction(),
                userJpa.getHashedPassword(),userJpa.getPhoto(),
                userJpa.getProfileID());
    }
}
