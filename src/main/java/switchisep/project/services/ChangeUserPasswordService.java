package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.EditUserDTO;
import switchisep.project.dto.UserUIInfoDTO;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.UserID;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.Optional;

@Service
public class ChangeUserPasswordService {


    UserDomainInternalDTOAssembler userUIInfoDTOAssembler;

    ViewUsersService searchUserByIDService;

    UserRepositoryInterface userRepository;

    @Autowired
    ChangeUserPasswordService(ViewUsersService searchUserByIDService,
                              UserDomainInternalDTOAssembler userUIInfoDTOAssembler,
                              UserRepositoryInterface userRepository) {
        this.searchUserByIDService = searchUserByIDService;
        this.userUIInfoDTOAssembler = userUIInfoDTOAssembler;
        this.userRepository = userRepository;
    }

    public Optional<UserUIInfoDTO> setNewPassword(EditUserDTO editUserDTO) {

        boolean passWasSet = false;

        UserID userID = UserID.createUserID(editUserDTO.userID);

        Optional<User> userOptional = userRepository.findById(userID);

        if (userOptional.isPresent()) {

            User user = userOptional.get();

            passWasSet = user.setNewPassword(editUserDTO.newPassword, editUserDTO.oldPassword);

            if (passWasSet) {

                User editedUser = userRepository.save(user);

                UserUIInfoDTO alreadyEditedUserDTO = userUIInfoDTOAssembler.toNativeDTO(editedUser);

                return Optional.of(alreadyEditedUserDTO);
            }

        }

        return Optional.empty();
    }
}


