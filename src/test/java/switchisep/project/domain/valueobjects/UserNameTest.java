package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserNameTest {

    @Test
    void testUserNameConstructorWithEquals_ValidState(){

        //Arrange
        String userNameString = "User Name";

        //Act
        UserName userName = UserName.createUserName(userNameString);

        //Assert
       assertEquals(userName.getUserName(), userNameString);
    }

    @Test
    void testUserNameConstructorWithEquals_InvalidState_ContainsDigits(){

        //Arrange
        String userNameString = "User Name12";

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            UserName.createUserName(userNameString);
        });
    }

    @Test
    void testUserNameConstructorWithEquals_InvalidState_ContainsSpecialCharacters(){

        //Arrange
        String userNameString = "User Name%!$";

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            UserName.createUserName(userNameString);
        });
    }

    @Test
    void testUserNameConstructorWithEquals_InvalidState_DoesNotContainLetters(){

        //Arrange
        String userNameString = "%12##";

        //Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            UserName.createUserName(userNameString);
        });
    }

    @Test
    void testUserNameConstructorWithEquals_InvalidState_EmptyString(){

        //Arrange
        String userNameString = "";

        //Act & Assert
        Assertions.assertThrows(IllegalStateException.class, () -> {
            UserName.createUserName(userNameString);
        });
    }

    @Test
    void testUserNameConstructorWithEquals_InvalidState_EmptySpacedString(){

        //Arrange
        String userNameString = "   ";

        //Act & Assert
        Assertions.assertThrows(IllegalStateException.class, () -> {
            UserName.createUserName(userNameString);
        });
    }

    @Test
    void testUserNameConstructorWithEquals_InvalidState_Null(){

        //Arrange
        String userNameString = null;

        //Act & Assert
        Assertions.assertThrows(IllegalStateException.class, () -> {
            UserName.createUserName(userNameString);
        });
    }

    @Test
    void getUserNameString(){

        //Arrange
        String userNameString = "User Name";
        UserName userName = UserName.createUserName(userNameString);

        //Act
        String userNameFound = userName.getUserName();

        //Assert
        assertEquals(userNameString, userNameFound);
    }

    //----------------------------------------------------------------------------------------------------------------//
    //Tests for equals and hashCode methods

    @Test
    void testSamValueAs_Null(){

        //Arrange
        String userNameString = "User Name";
        UserName userName = UserName.createUserName(userNameString);

        //Act
        boolean result = userName.sameValueAs(null);

        //Assert
        assertFalse(result);
    }


    @Test
    void testEqualsForSameObject(){

        //Arrange
        String userNameString = "User Name";

        //Act
        UserName userName = UserName.createUserName(userNameString);

        //Assert
        assertEquals(userName,userName);
    }

    @Test
    void testEqualsForEqualObject(){

        //Arrange
        String userNameString = "User Name";

        //Act
        UserName userName1 = UserName.createUserName(userNameString);
        UserName userName2 = UserName.createUserName(userNameString);

        //Assert
        assertEquals(userName1,userName2);
    }

    @Test
    void testEqualsForDifferentObject_DifferentClass(){

        //Arrange
        String userNameString = "User Name";

        //Act
        UserName userName1 = UserName.createUserName(userNameString);
        Email email = Email.createEmail("email@email.com");

        //Assert
        assertNotEquals(userName1,email);
    }

    @Test
    void testEqualsForDifferentObject_SameClass(){

        //Arrange
        String userNameString = "User Name";
        String userNameString2 = "User Name Two";

        //Act
        UserName userName1 = UserName.createUserName(userNameString);
        UserName userName2 = UserName.createUserName(userNameString2);

        //Assert
        assertNotEquals(userName1,userName2);
    }

    @Test
    void testHashCodeForDifferentObject_SameClass(){

        //Arrange
        String userNameString = "User Name";
        String userNameString2 = "User Name Two";

        //Act
        UserName userName1 = UserName.createUserName(userNameString);
        UserName userName2 = UserName.createUserName(userNameString2);

        //Assert
        assertNotEquals(userName1.hashCode(),userName2.hashCode());

    }

    @Test
    void testHashCodeForSameObject(){

        //Arrange
        String userNameString = "User Name";

        //Act
        UserName userName1 = UserName.createUserName(userNameString);

        //Assert
        assertEquals(userName1.hashCode(),userName1.hashCode());

    }
}