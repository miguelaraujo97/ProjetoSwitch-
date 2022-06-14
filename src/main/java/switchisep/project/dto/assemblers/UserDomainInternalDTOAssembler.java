package switchisep.project.dto.assemblers;

import org.springframework.stereotype.Service;
import switchisep.project.dto.UserInternalDTO;
import switchisep.project.dto.UserUIInfoDTO;
import switchisep.project.dto.UserUiDTO;
import switchisep.project.domain.user.User;

@Service
public class UserDomainInternalDTOAssembler {

    private UserDomainInternalDTOAssembler() {
    }

    public UserInternalDTO toDTO(User user) {
        return new UserInternalDTO(user.getUserID(), user.getEmail(),
                user.getUserName(), user.getProfileID(),
                user.getFunction(), user.getPhoto(),
                user.getUserStatus());
    }

    public UserUIInfoDTO toNativeDTO(User user){

        UserUIInfoDTO userUIInfoDTO = new UserUIInfoDTO();

        userUIInfoDTO.userId = user.getUserID().getUserID();
        userUIInfoDTO.profileId = user.getProfileID().getProfileID();
        userUIInfoDTO.name = user.getUserName().getUserName();
        userUIInfoDTO.email = user.getEmail().getEmail();
        userUIInfoDTO.function=user.getFunction().getFunction();
        userUIInfoDTO.photo= user.getPhoto().getPhotoURL();

        return userUIInfoDTO;
    }

    public UserUiDTO toOutputDTO (User user){
        return new UserUiDTO(user.getUserID().getUserID(),
                user.getUserName().getUserName(),
                user.getEmail().getEmail(),
                user.getFunction().getFunction() );
    }
}