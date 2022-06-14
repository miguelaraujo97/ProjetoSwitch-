package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.UserID;
import switchisep.project.repositories.interfaces.UserRepositoryInterface;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivateNewUserServiceTest {

    @InjectMocks
    ActivateNewUserService activateNewUserService;

    @Mock
    UserRepositoryInterface userRepository;

    @Test
    void activateNewUser_UnitTest_FailureScenario_UserIsNotFound(){
        //Arrange
        UserID userID = UserID.createUserID(1);
        when(userRepository.findById(userID)).thenReturn(Optional.empty());

        //Act
        boolean result = activateNewUserService.activateNewUser(1);

        //Assert
        assertFalse(result);
    }

    @Test
    void activateNewUser_UnitTest_SuccessScenario_UserIsActivated(){
        //Arrange
        UserID userID = UserID.createUserID(1);

        User userDouble = mock(User.class);

        when(userRepository.findById(userID)).thenReturn(Optional.of(userDouble));

        //Act
        boolean result = activateNewUserService.activateNewUser(1);

        //Assert
        assertTrue(result);
    }
}