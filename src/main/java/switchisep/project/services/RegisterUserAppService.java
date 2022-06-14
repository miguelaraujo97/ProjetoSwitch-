package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.UserInternalDTO;
import switchisep.project.dto.UserRegistrationRequest;
import switchisep.project.dto.UserUIInfoDTO;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;
import switchisep.project.domain.domainservices.DefaultProfileDomainService;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.user.User;
import switchisep.project.domain.user.UserFactoryInterface;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.List;
import java.util.Optional;

@Service
public class RegisterUserAppService {

    @Autowired
    private UserRepositoryInterface userRepositoryInterface;
    @Autowired
    private UserDomainInternalDTOAssembler userDTOAssembler;
    @Autowired
    private UserFactoryInterface userFactoryInterface;
    @Autowired
    private ProfileRepositoryInterface profileRepository;
    @Autowired
    DefaultProfileDomainService defaultProfileDomainService;
    @Autowired
    UserDomainInternalDTOAssembler userUIInfoDTOAssembler;


    public Optional<UserUIInfoDTO> createAndSaveUser(UserRegistrationRequest
                                                       userRegistrationRequest) {

        Email email = Email.createEmail(userRegistrationRequest.email);

        UserID userID = UserID.createUserID(userRepositoryInterface.findAll().size()+1);

        PasswordHash passwordHash = PasswordHash.createHashPassword(
                userRegistrationRequest.password);
        UserName userName = UserName.createUserName(
                userRegistrationRequest.userName);


        Function userFunction = Function.
                createFunction(userRegistrationRequest.function);
        Photo userPhoto = Photo.
                createPhoto(userRegistrationRequest.photo);


        List<Profile> profileList = profileRepository.getAllProfiles();

        Optional <ProfileID> opDefaultProfileID = defaultProfileDomainService.getDefaultProfileID(profileList);

        Optional<UserInternalDTO> opUser = findByEmail(email);

        if (!opUser.isPresent() && opDefaultProfileID.isPresent()) {
            User newUser = userFactoryInterface.createNewUser(userID,
                    userName, email, userFunction, passwordHash,
                    userPhoto, opDefaultProfileID.get());

            User newUserSaved = userRepositoryInterface.save(newUser);

            UserUIInfoDTO newUserSavedDTO = userUIInfoDTOAssembler.toNativeDTO(newUserSaved);

            return Optional.of(newUserSavedDTO);
        }
        return Optional.empty();
    }

    public Optional<UserInternalDTO> findByEmail(Email email) {

        Optional<User> opUser = userRepositoryInterface.findByEmail(email);
        if (opUser.isPresent()) {
            return Optional.of(userDTOAssembler.toDTO(opUser.get()));
        }
        return Optional.empty();
    }
}
