package switchisep.project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserUiDTOTest {

    @Test
    void isTheUserUIDTOCreatedEqualToItself(){

        //Arrange
        int id = 1;
        String userName = "UserNameTest";
        String email = "test@email.com";
        String function = "Developer";
        UserUiDTO userUiDTO = new UserUiDTO(id, userName, email, function);

        //Act
        boolean result = userUiDTO.equals(userUiDTO);

        //Assert
        assertTrue(result);
    }

    @Test
    void areBothUserUIDTOsEqual() {

        //Arrange
        int id = 1;
        String userName = "UserNameTest";
        String email = "test@email.com";
        String function = "Developer";

        //Act
        UserUiDTO userUiDTO = new UserUiDTO(id, userName, email, function);
        UserUiDTO userUiDTO1 = new UserUiDTO(id, userName, email, function);

        //Assert
        assertEquals(userUiDTO, userUiDTO1);
    }

    @Test
    void areBothUserUIDTOsDifferent() {

        //Arrange
        int id = 1;
        String userName = "UserNameTest";
        String userName1 = "UserName";
        String email = "test@email.com";
        String function = "Developer";

        //Act
        UserUiDTO userUiDTO = new UserUiDTO(id, userName, email, function);
        UserUiDTO userUiDTO1 = new UserUiDTO(id, userName1, email, function);

        //Assert
        assertNotEquals(userUiDTO, userUiDTO1);
    }

    @Test
    void isOneOfTheUserUIsNull() {

        //Arrange
        int id = 1;
        String userName = "UserNameTest";
        String email = "test@email.com";
        String function = "Developer";

        //Act
        UserUiDTO userUiDTO = new UserUiDTO(id, userName, email, function);
        UserUiDTO userUiDTO1 = null;

        //Assert
        assertNotEquals(userUiDTO, userUiDTO1);
    }

    @Test
    void areBothHashCodesEqual() {

        //Arrange
        int id = 1;
        String userName = "UserNameTest";
        String email = "test@email.com";
        String function = "Developer";

        //Act
        UserUiDTO userUiDTO = new UserUiDTO(id, userName, email, function);
        UserUiDTO userUiDTO1 = new UserUiDTO(id, userName, email, function);

        //Assert
        assertEquals(userUiDTO.hashCode(), userUiDTO1.hashCode());
    }

    @Test
    void areBothHashCodesDifferent() {

        //Arrange
        int id = 1;
        String userName = "UserNameTest";
        String userName1 = "UserName";
        String email = "test@email.com";
        String function = "Developer";

        //Act
        UserUiDTO userUiDTO = new UserUiDTO(id, userName, email, function);
        UserUiDTO userUiDTO1 = new UserUiDTO(id, userName1, email, function);

        //Assert
        assertNotEquals(userUiDTO.hashCode(), userUiDTO1.hashCode());
    }

}