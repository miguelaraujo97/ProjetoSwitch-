package switchisep.project.dto.assemblers;

import org.junit.jupiter.api.Test;
import switchisep.project.dto.ProfileRequestDTO;
import switchisep.project.domain.profilerequest.ProfileRequest;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.domain.valueobjects.UserID;

import static org.junit.jupiter.api.Assertions.*;

class ProfileRequestDTOAssemblerTest {
    @Test
    void toNative_success() {
        ProfileRequestDTOAssembler profileDTOAssembler = new ProfileRequestDTOAssembler();

        ProfileRequestID profileRequestID = ProfileRequestID.createProfileRequestID(1);
        ProfileID profileID = ProfileID.generateID();
        UserID userID = UserID.createUserID(1);
        ProfileRequest profileRequest = new ProfileRequest.Builder(profileRequestID,
                profileID, userID).build();

        String profID = profileID.getProfileID();
        int prfRID = profileRequestID.getProfileRequestID();

        ProfileRequestDTO expected = new ProfileRequestDTO(1, profID, 1);

        ProfileRequestDTO result = profileDTOAssembler.toDTO(profileRequest);

        //assert
        assertEquals(expected, result);
    }
}