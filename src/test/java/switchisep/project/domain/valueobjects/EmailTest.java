package switchisep.project.domain.valueobjects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @ParameterizedTest
    @ValueSource(strings = {" ", "     ", "\t", "\n"})
    void createInvalidEmail_invalid_spaces(String emailTest) {
        //Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Email.createEmail(emailTest);
                });
        String expectedMessage = "Email cannot be null or empty";
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }



    @ParameterizedTest
    @ValueSource(strings = {"abcd@abcd.com.x.a",
            "someting@something",
            "email@email@email",
            "email.a.test@email.",
            "email.a.test@email.#",
            "user'name@domain.com.pt",
            "user-name@domaincom",
            "user@user@yahoo.com",
            "user#domain.com",
            ".", "@"})
    void createInvalidEmail_invalid_formats(String emailTest) {
        //Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    Email.createEmail(emailTest);
                });
        String expectedMessage = "Email does not meet email format";
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"abcd@abcd.com",
            "user@domaincom.pt",
            "user.name@domain.com",
            "user-test@domain.com",
            "abcd@abcd.com.pt"})
    void createValidEmail(String emailTest) {
        //Assert
        //Act
        Email email = Email.createEmail(emailTest);
        String expectedEmail = email.getEmail();
        //Assert
        assertEquals(expectedEmail, emailTest);
    }

    @Test
    void validatesEmailForNull() {
        //Arrange
        String emailTest = null;
        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
            Email.createEmail(emailTest);
            ;
        });
        String expectedMessage = "Email cannot be null or empty";
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void validatesEmailForEmpty() {
        //Arrange
        String emailTest = "";
        //Act & Assert
        Exception exception =
                Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Email.createEmail(emailTest);

        });
        String expectedMessage = "Email cannot be null or empty";
        //Act
        String actualMessage = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, actualMessage);
    }

    //----------------------------------------------------------------------------------------------------------------//
    //Tests for equals and hashCode methods

    @Test
    void testSamValueAs_Null(){

        //Arrange
        String emailTest = "user@yahoo.com";
        Email email = Email.createEmail(emailTest);

        //Act
        boolean result = email.sameValueAs(null);

        //Assert
        assertFalse(result);
    }

    @Test
    void testEqualsForSameObject() {

        //Arrange
        String emailTest = "user@yahoo.com";

        //Act
        Email email = Email.createEmail(emailTest);

        //Assert
        assertEquals(email, email);
    }

    @Test
    void testGetMethod() {

        //Arrange
        String emailTest = "user@yahoo.com";

        //Act
        Email email = Email.createEmail(emailTest);
        String result = email.getEmail();
        //Assert
        assertEquals(emailTest, result);
    }

    @Test
    void testEqualsForEqualObject() {

        //Arrange
        String emailTest = "user@yahoo.com";
        String emailTest2 = "user@yahoo.com";

        //Act
        Email email1 = Email.createEmail(emailTest);
        Email email2 = Email.createEmail(emailTest2);

        //Assert
        assertEquals(email1, email2);
    }

    @Test
    void testEqualsForDifferentObject_DifferentClass() {

        //Arrange
        String emailTest = "user@gmail.com";

        //Act
        Email email = Email.createEmail(emailTest);
        Object obj = new Object();

        //Assert
        assertNotEquals(email, obj);
    }

    @Test
    void testEqualsForDifferentObject_SameClass() {

        //Arrange
        String emailTest = "user@yahoo.com";
        String emailTest2 = "user2@yahoo.com";

        //Act
        Email email1 = Email.createEmail(emailTest);
        Email email2 = Email.createEmail(emailTest2);

        //Assert
        assertNotEquals(email1, email2);
    }

    @Test
    void testHashCodeForDifferentObject_SameClass() {

        //Arrange
        String emailTest = "usera@yahoo.com";
        String emailTest2 = "user2@yahoo.com";

        //Act
        Email email1 = Email.createEmail(emailTest);
        Email email2 = Email.createEmail(emailTest2);

        //Assert
        assertNotEquals(email1.hashCode(), email2.hashCode());

    }

    @Test
    void testHashCodeForSameObject() {

        //Arrange
        String emailTest = "user@yahoo.com";

        //Act
        Email email = Email.createEmail(emailTest);

        //Assert
        assertEquals(email.hashCode(), email.hashCode());

    }


}