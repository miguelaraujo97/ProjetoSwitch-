package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.EditUserDTO;
import switchisep.project.dto.UserUIInfoDTO;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.Email;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.Optional;

@Service
public class EditUserService {
    @Autowired
    UserRepositoryInterface userRepository;
    @Autowired
    UserDomainInternalDTOAssembler userUIInfoDTOAssembler;

    public Optional<UserUIInfoDTO> setNewUserData(EditUserDTO editUserDTO) {

        Email userEmail = Email.createEmail(editUserDTO.userEmail);

        Optional<User> userOptional = userRepository.findByEmail(userEmail);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (!(editUserDTO.function == null || editUserDTO.function.isBlank())) {
                user.editUserFunction(editUserDTO.function);
            }
            if (!(editUserDTO.photo == null || editUserDTO.photo.isBlank())) {
                user.editUserPhoto(editUserDTO.photo);
            }

            UserUIInfoDTO alreadyEditedUserDTO =
                    userUIInfoDTOAssembler.toNativeDTO(user);


            return Optional.of(alreadyEditedUserDTO);
        } else {
            return Optional.empty();
        }
    }
}
