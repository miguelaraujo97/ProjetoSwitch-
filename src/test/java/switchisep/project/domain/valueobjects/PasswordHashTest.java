package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHashTest {

    @Test
    void createHashedPassword_emptyNull() {

        // arrange
        // act & assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                PasswordHash.createHashPassword(null)
        );
        assertEquals("Invalid password.", exception.getMessage());
    }


    @Test
    void createHashedPassword_emptyString() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                PasswordHash.createHashPassword("")
        );
        assertEquals("Invalid password.", exception.getMessage());

    }

    @Test
    void createHashedPassword_emptySpacedString() {

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                PasswordHash.createHashPassword("   ")
        );
        assertEquals("Invalid password.", exception.getMessage());

    }


    @Test
    void checkPasswordHash() {

        // arrange
        PasswordHash pass = PasswordHash.createHashPassword("test");

        // act & assert
        assertTrue(pass.checkSecurePasswordHash("test"));
    }

    @Test
    void checkPasswordHashFalse() {

        // arrange
        PasswordHash pass = PasswordHash.createHashPassword("test");

        // act & assert
        assertFalse(pass.checkSecurePasswordHash("34"));
    }

    @Test
    void checkNewPasswordWithInvalidOldPasswordNull() {

        // arrange
        PasswordHash pass = PasswordHash.createHashPassword("Test");

        // act & assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                pass.checkSecurePasswordHash(null)
        );
        assertEquals("Invalid written / hashed provided for comparison.", exception.getMessage());
    }



    @Test
    void sameValueAs(){

        //Arrange
        PasswordHash pass = PasswordHash.createHashPassword("test");

        //Act
        boolean result = pass.sameValueAs(pass);

        //Assert
        assertTrue(result);

    }

    @Test
    void sameValueAs_False(){

        //Arrange
        PasswordHash pass = PasswordHash.createHashPassword("test");

        //Act
        boolean result = pass.sameValueAs(null);

        //Assert
        assertFalse(result);

    }

}