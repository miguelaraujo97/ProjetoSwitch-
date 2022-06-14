package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.UserID;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.Optional;

/**
 * Service associated with US002.
 * Responsible for telling a specific user to change its status to true.
 */
@Service
public class ActivateNewUserService {

    private UserRepositoryInterface userRepositoryInterface;

    /**
     * Given email string, creates UserID VO and searches the system by id.
     * If user is present, activates it.
     * @param id
     * @return true if user is activated. False if user is not found.
     */
    public boolean activateNewUser(int id){

        UserID userID = UserID.createUserID(id);

        Optional<User> opUser = userRepositoryInterface.findById(userID);

        boolean wasActivated = false;

        if(opUser.isPresent()){
            opUser.get().changeUserStatus(true);
            wasActivated = true;
        }

        return  wasActivated;
    }
}
