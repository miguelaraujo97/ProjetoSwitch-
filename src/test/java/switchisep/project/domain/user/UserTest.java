package switchisep.project.domain.user;

import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserTest {

    //----------------------------------------------------//---------------------------------------------------------//
    //US002 - As Non-Registered User, I want to activate a just registered user account.
    //US025 - As Administrator, I want to inactivate a user account.
    //US026 - As Administrator, I want to activate a user account.

    @Test
    void changeUserStatus_SuccessCase_InactivateToActive() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");

        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user.changeUserStatus(true);

        //Assert
        assertTrue(result);
    }

    @Test
    void changeUserStatus_SuccessCase_ActiveToInactive() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        user.changeUserStatus(true);

        //Act
        boolean result = user.changeUserStatus(false);

        //Assert
        assertTrue(result);
    }

    @Test
    void changeUserStatus_FailureCase_InactiveToInactive() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user.changeUserStatus(false);

        //Assert
        assertFalse(result);
    }

    @Test
    void changeUserStatus_FailureCase_ActiveToActive() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        user.changeUserStatus(true);

        //Act
        boolean result = user.changeUserStatus(true);

        //Assert
        assertFalse(result);
    }

    //----------------------------------------------------//---------------------------------------------------------//
    //US004 - As Administrator, I want to search for users

    //Search by Profile ID
    @Test
    void checkIfUserHasGivenProfileID_SuccessCase_UserHasGivenProfileID(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user.checkIfUserHasGivenProfileID(profileIdDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void checkIfUserHasGivenProfileID_FailureCase_UserDoesNotHaveGivenProfileID(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);
        ProfileID profileIdToCheckDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user.checkIfUserHasGivenProfileID(profileIdToCheckDouble);

        //Assert
        assertFalse(result);
    }

    //Search by Email

    @Test
    void isEmailEqualTo_SuccessCase_EmailIsEqualToUsers(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        Email emailToTest = Email.createEmail("test@email.com");

        //Act
        boolean result = user.isEmailEqualTo(emailToTest);

        //Assert
        assertTrue(result);
    }

    @Test
    void isEmailEqualTo_FailureCase_EmailIsNotEqualToUsers(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        Email emailToTest = Email.createEmail("testDifferent@email.com");

        //Act
        boolean result = user.isEmailEqualTo(emailToTest);

        //Assert
        assertFalse(result);
    }

    //----------------------------------------------------//---------------------------------------------------------//
    //US006 - As Administrator, I want to update profiles assigned to a user account.

    @Test
    void changeProfile_SuccessCase_ProfileIDInUserIsDifferent() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        ProfileID profileIdToChangeDouble = mock(ProfileID.class);

        //Act
        boolean result = user.changeProfile(profileIdToChangeDouble);

        //Assert
        assertTrue(result);
    }

    @Test
    void changeProfile_FailureCase_ProfileIDInUserIsTheSame() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user.changeProfile(profileIdDouble);

        //Assert
        assertFalse(result);
    }

    //----------------------------------------------------//---------------------------------------------------------//
    //US010 - As Authenticated User, I want to update its own data (e.g., photo, function).
    //Edit Photo

    @Test
    void editUserPhoto_SuccessCase_PhotoFieldWasEmpty() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, null, profileIdDouble);

        String photoURL = "https://www.isep.ipp.pt/img/logo3.png";

        //Act
        boolean result = user.editUserPhoto(photoURL);

        //Assert
        assertTrue(result);
    }

    @Test
    void editUserPhoto_SuccessCase_NewPhotoURLIsDifferent() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user.editUserPhoto("https://www.isep.ipp.pt/img/logo4.png");

        //Assert
        assertTrue(result);
    }

    @Test
    void editUserPhoto_FailureCase_NewPhotoIsTheSameAsOldOne() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user.editUserPhoto("https://www.isep.ipp.pt/img/logo3.png");

        //Assert
        assertFalse(result);
    }

    //Edit Function
    @Test
    void editUserFunction_SuccessCase_NewFunctionIsDifferent() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);


        //Act
        boolean result = user.editUserFunction("Tester");

        //Assert
        assertTrue(result);
    }

    @Test
    void editUserFunction_FailureCase_NewFunctionIsTheSame() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);


        //Act
        boolean result = user.editUserFunction("Developer");

        //Assert
        assertFalse(result);
    }

    //----------------------------------------------------//---------------------------------------------------------//
    //US011 - As Authenticated User, I want to change his/her password.

    @Test
    void setNewPassword_SuccessCase_PasswordGuessIsCorrect(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user.setNewPassword("newPasswordForTesting", "passwordForTesting");

        //Assert
        assertTrue(result);
    }

    @Test
    void setNewPassword_FailureCase_PasswordGuessIsIncorrect(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user.setNewPassword("newPasswordForTesting", "wrongGuess");

        //Assert
        assertFalse(result);
    }

    //----------------------------------------------------//---------------------------------------------------------//
    //Tests for sameIdentityAsMethod

    @Test
    void testSameIdentityAs_SameIdentity(){

        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        ProfileID profileIdDouble = mock(ProfileID.class);

        User user1 = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        Email email2 = Email.createEmail("test@email.com");
        UserID userId2 = UserID.createUserID(1);

        User user2 = new User(userId2, userName, email2,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user1.sameIdentityAs(user2);

        //Assert
        assertTrue(result);
    }

    @Test
    void testSameIdentityAs_DifferentIdentity(){

        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        ProfileID profileIdDouble = mock(ProfileID.class);

        User user1 = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        Email email2 = Email.createEmail("testDiff@email.com");
        UserID userId2 = UserID.createUserID(2);

        User user2 = new User(userId2, userName, email2,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean result = user1.sameIdentityAs(user2);

        //Assert
        assertFalse(result);
    }

    @Test
    void testSameIdentityAs_Null(){

        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        ProfileID profileIdDouble = mock(ProfileID.class);

        User user1 = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        Email email2 = Email.createEmail("testDiff@email.com");
        UserID userId2 = UserID.createUserID(2);

        User userNull = null;

        //Act
        boolean result = user1.sameIdentityAs(userNull);

        //Assert
        assertFalse(result);
    }

    //----------------------------------------------------//---------------------------------------------------------//
    //Tests for equals and hashcode methods

    @Test
    void testEquals_SameObject() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act & Assert
        assertEquals(user, user);
    }

    @Test
    void testEquals_DifferentClass() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        Object nativeObject = new Object();

        //Act & Assert
        assertNotEquals(user, nativeObject);
    }

    @Test
    void testEquals_Null() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        User userNull = null;

        //Act & Assert
        assertNotEquals(user, null);
    }

    @Test
    void testEquals_EqualObjectUserClass() {

        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);

        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user1 = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        User user2 = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act & Assert
        assertEquals(user1, user2);
    }

    @Test
    void testEquals_DifferentObjectUserClass() {

        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email1 = Email.createEmail("test@email.com");
        UserID userId1 = UserID.createUserID(1);

        Email email2 = Email.createEmail("test2@email.com");
        UserID userId2 = UserID.createUserID(2);

        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user1 = new User(userId1, userName, email1,
                function, hashedPassword, photo, profileIdDouble);

        User user2 = new User(userId2, userName, email2,
                function, hashedPassword, photo, profileIdDouble);

        //Act & Assert
        assertNotEquals(user1, user2);
    }

    @Test
    void testHashCode_DifferentObjectUserClass() {
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email1 = Email.createEmail("test@email.com");
        UserID userId1 = UserID.createUserID(1);

        Email email2 = Email.createEmail("test2@email.com");
        UserID userId2 = UserID.createUserID(2);

        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user1 = new User(userId1, userName, email1,
                function, hashedPassword, photo, profileIdDouble);

        User user2 = new User(userId2, userName, email2,
                function, hashedPassword, photo, profileIdDouble);

        //Act & Assert
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    //----------------------------------------------------//---------------------------------------------------------//
    //Tests for getters

    @Test
    void getUserName(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        UserName userNameFound = user.getUserName();

        //Assert
        assertEquals(userName, userNameFound);
    }

    @Test
    void getUserID(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        UserID userIDFound = user.getUserID();

        //Assert
        assertEquals(userId, userIDFound);
    }

    @Test
    void getUserStatus_StatusIsFalse(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        boolean userStatusFound = user.getUserStatus();

        //Assert
        assertFalse(userStatusFound);
    }

    @Test
    void getUserStatus_StatusIsTrue(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        user.changeUserStatus(true);

        //Act
        boolean userStatusFound = user.getUserStatus();

        //Assert
        assertTrue(userStatusFound);
    }

    @Test
    void getUserEmail(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        Email emailFound = user.getEmail();

        //Assert
        assertEquals(email, emailFound);
    }

    @Test
    void getUserHashedPassword(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        PasswordHash passwordHash = user.getHashedPassword();

        //Assert
        assertEquals(hashedPassword, passwordHash);
    }

    @Test
    void testIfAPassWasSetAndWhenCheckingUsesTheLatestSaved(){
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("pass1");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        user.setNewPassword("pass2","pass1");
        user.setNewPassword("pass3","pass2");

        PasswordHash passwordHash = user.getHashedPassword();

        //Assert
        assertTrue(user.checkPasswordHash("pass3"));
    }

    @Test
    void getUserPhoto(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        Photo userPhoto = user.getPhoto();

        //Assert
        assertEquals(photo, userPhoto);
    }

    @Test
    void getUserFunction(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        Function userFunction = user.getFunction();

        //Assert
        assertEquals(userFunction, function);
    }

    @Test
    void getUserProfileID(){
        //Arrange
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("test@email.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");

        ProfileID profileIdDouble = mock(ProfileID.class);

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileIdDouble);

        //Act
        ProfileID profileIDDoubleFound = user.getProfileID();

        //Assert
        assertEquals(profileIdDouble, profileIDDoubleFound);
    }

}