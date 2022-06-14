package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.ProfileRequestDTO;
import switchisep.project.dto.UIProfileRequestDTO;
import switchisep.project.dto.assemblers.ProfileRequestDTOAssembler;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.profilerequest.ProfileRequest;
import switchisep.project.domain.profilerequest.ProfileRequestFactory;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.ProfileRequestID;
import switchisep.project.domain.valueobjects.UserID;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;
import switchisep.project.repositories.interfaces.ProfileRequestRepositoryInterface;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.Optional;

@Service
public class ProfileRequestService {

    @Autowired
    ProfileRepositoryInterface profileRepositoryInterface;
    @Autowired
    UserRepositoryInterface userRepositoryInterface;
    @Autowired
    ProfileRequestFactory profileRequestFactory;
    @Autowired
    ProfileRequestRepositoryInterface profileRequestRepository;
    @Autowired
    ProfileRequestDTOAssembler profileRequestDTOAssembler;

    public Optional<ProfileRequestDTO> createAndSaveProfileRequest(
            UIProfileRequestDTO uiProfileRequestDTO) {


        UserID userID = UserID.createUserID(uiProfileRequestDTO.
                userID);
        Name name = Name.createName(uiProfileRequestDTO.
                profileName);

        Optional<Profile> optProfile = profileRepositoryInterface.findByName(name);

        if (optProfile.isPresent()
                && userRepositoryInterface.findById(userID).isPresent()) {
            ProfileRequestID profileRequestID =
                    ProfileRequestID.createProfileRequestID(
                            profileRequestRepository.findAll().size() + 1);

            ProfileRequest profileRequest = profileRequestFactory.
                    createProfileRequest(profileRequestID,
                            optProfile.get().getProfileID(), userID);

            ProfileRequest profileRequest1 =
                    profileRequestRepository.save(profileRequest);

            Optional<ProfileRequestDTO> profileRequestDTO =
                    Optional.of(profileRequestDTOAssembler.
                            toDTO(profileRequest1));
            return profileRequestDTO;
        }
        return Optional.empty();
    }
}
