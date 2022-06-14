package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.UserUIInfoDTO;
import switchisep.project.dto.UserUiDTO;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.Email;
import switchisep.project.domain.valueobjects.Name;
import switchisep.project.domain.valueobjects.UserID;
import switchisep.project.repositories.interfaces.ProfileRepositoryInterface;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViewUsersService {

    private UserRepositoryInterface userRepository;

    private ProfileRepositoryInterface profileRepository;

    private UserDomainInternalDTOAssembler userDomainInternalDTOAssembler;

    public ViewUsersService(UserRepositoryInterface userRepository,
                            ProfileRepositoryInterface profileRepository,
                            UserDomainInternalDTOAssembler userDomainInternalDTOAssembler) {

        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.userDomainInternalDTOAssembler = userDomainInternalDTOAssembler;
    }

    public Optional<UserUiDTO> getUserByEmail(String email) {

        Email emailToFind = Email.createEmail(email);
        Optional<User> opUser = userRepository.findByEmail(emailToFind);
        if (opUser.isPresent()) {
            User user = opUser.get();
            return Optional.of(userDomainInternalDTOAssembler.toOutputDTO(user));
        } else {
            return Optional.empty();
        }
    }

    public List<UserUiDTO> getUserByProfile(String profileName) {
        Name profileNameToFind = Name.createName(profileName);
        Optional<Profile> profileToFind = profileRepository.findByName(profileNameToFind);

        List<User> userList = userRepository.findByProfileID(profileToFind.get().getProfileID());

        List<UserUiDTO> userListDto = new ArrayList<>();

        for (User user : userList) {
            userListDto.add(userDomainInternalDTOAssembler.toOutputDTO(user));
        }

        return userListDto;
    }

    public Optional<UserUIInfoDTO> getUserByID(int userID) {

        UserID userIDToSearch = UserID.createUserID(userID);

        Optional<User> opUser = userRepository.findById(userIDToSearch);
        if (opUser.isPresent()) {

            User user = opUser.get();

            UserUIInfoDTO changePasswordToUIDTO = userDomainInternalDTOAssembler.toNativeDTO(user);
            return Optional.of(changePasswordToUIDTO);
        } else {
            return Optional.empty();
        }

    }

    public List<UserUiDTO> getAllUsers() {

        List<UserUiDTO> userUiDTOSList = new ArrayList<>();

        List<User> userList = userRepository.findAll();

        for ( User user : userList) {

            UserUiDTO userUiDTO = userDomainInternalDTOAssembler.toOutputDTO(user);

            userUiDTOSList.add(userUiDTO);
        }

        return userUiDTOSList;
    }

}
