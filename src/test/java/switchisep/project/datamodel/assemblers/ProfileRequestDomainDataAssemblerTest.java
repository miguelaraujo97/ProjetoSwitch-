package switchisep.project.datamodel.assemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.datamodel.ProfileRequestJpa;
import switchisep.project.domain.profilerequest.ProfileRequest;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.domain.valueobjects.UserID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProfileRequestDomainDataAssemblerTest {


    @InjectMocks
    ProfileRequestDomainDataAssembler profileRequestDomainDataAssembler;

    @Test
    void testToData() {

        ProfileID profileIDAdmin = ProfileID.generateID();
        UserID userID = UserID.createUserID(1);
        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);
        ProfileRequest profileRequest =
                new ProfileRequest.Builder(profileRequestID,
                        profileIDAdmin, userID).build();

        ProfileRequestJpa profileRequestJpa = new ProfileRequestJpa(profileRequestID, profileIDAdmin, userID);

        ProfileRequestJpa profileRequestJpa1 = profileRequestDomainDataAssembler.toData(profileRequest);

        assertEquals(profileRequestJpa.getProfileID(), profileRequestJpa1.getProfileID());
        assertEquals(profileRequestJpa.getProfileRequestID(), profileRequestJpa1.getProfileRequestID());
        assertEquals(profileRequestJpa.getUserID(), profileRequestJpa1.getUserID());
    }

    @Test
    void testToDomain() {

        ProfileID profileIDAdmin = ProfileID.generateID();
        UserID userID = UserID.createUserID(1);
        ProfileRequestID profileRequestID =
                ProfileRequestID.createProfileRequestID(1);
        ProfileRequest profileRequest =
                new ProfileRequest.Builder(profileRequestID,
                        profileIDAdmin, userID).build();

        ProfileRequestJpa profileRequestJpa = new ProfileRequestJpa(profileRequestID, profileIDAdmin, userID);

        ProfileRequest result = profileRequestDomainDataAssembler.toDomain(profileRequestJpa);

        assertEquals(profileRequest, result);
    }
}