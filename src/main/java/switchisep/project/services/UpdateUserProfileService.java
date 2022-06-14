
package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.EditUserDTO;
import switchisep.project.dto.UserUiDTO;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;
import switchisep.project.domain.domainservices.UpdateProfileDomainService;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.UserID;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.Optional;

@Service
public class UpdateUserProfileService {
    final
    ProfileRepositoryInterface profileRepository;
    final
    UserRepositoryInterface userRepository;
    final
    UserDomainInternalDTOAssembler userToUiDTOAssembler;

    public UpdateUserProfileService(ProfileRepositoryInterface profileRepository,
                                    UserRepositoryInterface userRepository,
                                    UserDomainInternalDTOAssembler userToUiDTOAssembler) {

        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.userToUiDTOAssembler = userToUiDTOAssembler;
    }

    public Optional<UserUiDTO> profileUpdate(EditUserDTO editUserDTO) {
        Name profileName = Name.createName(editUserDTO.profileID);

        Optional<Profile> profile = profileRepository.findByName(profileName);

        UserID userID = UserID.createUserID(editUserDTO.userID);

        Optional<User> userToUpdate = userRepository.findById(userID);

        if (!profile.isPresent() || !userToUpdate.isPresent()) {
            return Optional.empty();
        }

        UpdateProfileDomainService updateProfileDomainService =
                new UpdateProfileDomainService(userToUpdate.get(), profile.get());

        Optional<User> opUserUpdated = updateProfileDomainService.updateProfile();

        if(!opUserUpdated.isPresent()){
            return Optional.empty();
        }

        User userUpdated= userRepository.save(opUserUpdated.get());

        return Optional.of(userToUiDTOAssembler.toOutputDTO(userUpdated));
    }

}
