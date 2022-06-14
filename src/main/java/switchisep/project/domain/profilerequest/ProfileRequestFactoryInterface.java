package switchisep.project.domain.profilerequest;

import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.domain.valueobjects.UserID;

public  interface ProfileRequestFactoryInterface {
    ProfileRequest createProfileRequest(ProfileRequestID profileRequestID,
                                        ProfileID profileID, UserID userID);
}
