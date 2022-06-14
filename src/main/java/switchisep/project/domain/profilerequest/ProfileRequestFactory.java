package switchisep.project.domain.profilerequest;

import org.springframework.stereotype.Service;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.domain.valueobjects.UserID;



@Service
public  class ProfileRequestFactory implements ProfileRequestFactoryInterface{
    public ProfileRequest createProfileRequest(ProfileRequestID profileRequestID,
                                               ProfileID profileID,
                                               UserID userID){
        return new ProfileRequest.Builder(profileRequestID,
                profileID,userID).build();
    }
}
