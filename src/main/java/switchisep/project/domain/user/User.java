package switchisep.project.domain.user;

import switchisep.project.domain.valueobjects.*;

import java.util.Objects;

/**
 * User class responsible for creating new User objects.
 * To create a new User, there needs to exist a valid
 * name, function, email, password and userID
 * for that User.
 * Email has to be unique in the system.
 */
public class User implements Entity<User> {


    private final UserID userID;
    private final Email email;
    private PasswordHash hashedPassword;
    private final UserName userName;
    private ProfileID profileID;
    private Function function;
    private Photo photo;
    private boolean userStatus;

    /**
     * User constructor.
     * Status is false by default upon creation.
     *
     * @param userID
     * @param userName
     * @param email
     * @param function
     * @param hashedPassword
     * @param photo
     * @param profileID
     */
    public User(UserID userID, UserName userName, Email email, Function function,
                PasswordHash hashedPassword, Photo photo, ProfileID profileID) {

        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.function = function;
        this.hashedPassword = hashedPassword;
        this.photo = photo;
        this.userStatus = false;
        this.profileID = profileID;
    }

    //US002 - As Non-Registered User, I want to activate a just registered user account.
    //US025 - As Administrator, I want to inactivate a user account.
    //US026 - As Administrator, I want to activate a user account.

    /**
     * Method that changes the user status.
     * If user already has the given status, returns false to indicate
     * the status was not changed.
     *
     * @param statusToChangeTo to change
     * @return true if status was changed, false otherwise
     */
    public boolean changeUserStatus(boolean statusToChangeTo) {

        boolean statusChange = false;

        if (userStatus != statusToChangeTo) {
            this.userStatus = !this.userStatus;
            statusChange = true;
        }
        return statusChange;
    }

    //US004 - As Administrator, I want to search for users

    /**
     * Method that checks if given profileID is the same as the profileID present
     * in the User.
     *
     * @param profileID
     * @return true if profileID is the same as the one given, false otherwise
     */
    public boolean checkIfUserHasGivenProfileID(ProfileID profileID) {
        return this.profileID.equals(profileID);
    }

    /**
     * Method to check if input email is equal to User object email.
     *
     * @param email
     * @return true if email Objects are equal, false otherwise.
     */
    public boolean isEmailEqualTo(Email email) {
        return Objects.equals(this.email, email);
    }

    //US006 - As Administrator, I want to update profiles assigned to a user account.

    /**
     * Method that changed the User's assigned Profile
     *
     * @param profileID
     * @return false if User already has the ProfileID being assigned
     */
    public boolean changeProfile(ProfileID profileID) {

        boolean profileWasChanged = true;

        if (this.profileID.equals(profileID)) {
            profileWasChanged = false;
        } else {
            this.profileID = profileID;
        }
        return profileWasChanged;
    }

    //US010 - As Authenticated User, I want to update its own data (e.g., photo, function).

    /**
     * Method that edits the user photo.
     *
     * @param photoURL
     * @return true if photo was changed, false if the new photo is the same
     * as the old one.
     */
    public boolean editUserPhoto(String photoURL) {

        boolean wasChanged = true;

        if (this.photo == null) {
            this.photo = Photo.createPhoto(photoURL);
        } else {
            Photo newPhoto = Photo.createPhoto(photoURL);
            if (this.photo.equals(newPhoto)) {
                wasChanged = false;
            } else {
                this.photo = newPhoto;
            }
        }
        return wasChanged;
    }

    /**
     * Method that edits the user function.
     *
     * @param userFunction
     * @return true if function was changed, false if the new function is the
     * same as the old one.
     */
    public boolean editUserFunction(String userFunction) {

        boolean wasChanged = true;
        Function newFunction = Function.createFunction(userFunction);

        if (this.function.equals(newFunction)) {
            wasChanged = false;
        } else {
            this.function = newFunction;
        }
        return wasChanged;
    }

    //US011 - As Authenticated User, I want to change his/her password.

    /**
     * Sets a new encrypted Password on the current user.
     *
     * @param newPassword
     * @param oldPassGuess
     * @return true if the password is changed, false otherwise.
     */
    public boolean setNewPassword(String newPassword, String oldPassGuess) {
        boolean newPassSet = false;
        if (checkPasswordHash(oldPassGuess)) {
            this.hashedPassword = PasswordHash.createHashPassword(newPassword);
            newPassSet = true;
        }
        return newPassSet;
    }

    /**
     * Method to compare a Password guess with the actual pass. This method will encrypt the password guess,
     * and compare it to the actual encrypted password. If the hashes match, then the guess is correct.
     *
     * @param writtenPasswordGuess String
     * @return boolean
     */
    public boolean checkPasswordHash(String writtenPasswordGuess) {
        return this.hashedPassword.checkSecurePasswordHash(writtenPasswordGuess);
    }

    /**
     * Getter method for userID. Necessary for DTO creation.
     */
    public UserID getUserID() {
        return userID;
    }

    /**
     * Getter method for email. Necessary for DTO creation.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Getter method for user name. Necessary for DTO creation.
     */
    public UserName getUserName() {
        return userName;
    }

    /**
     * Getter method for profileID. Necessary for DTO creation.
     */
    public ProfileID getProfileID() {
        return profileID;
    }

    /**
     * Getter method for function. Necessary for DTO creation.
     */
    public Function getFunction() {
        return function;
    }

    /**
     * Getter method for hashed password. Necessary for JPA object creation.
     */
    public PasswordHash getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Getter method for user photo. Necessary for DTO creation.
     */
    public Photo getPhoto() {
        return photo;
    }

    /**
     * Getter method for userStatus. Necessary for DTO creation.
     */
    public boolean getUserStatus() {
        return userStatus;
    }

    public void setProfile(ProfileID newProfileID) {
        this.profileID = newProfileID;
    }

    @Override
    public boolean sameIdentityAs(User other) {
        return other != null && userID.sameValueAs(other.userID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userStatus == user.userStatus && Objects.equals(userID, user.userID) &&
                Objects.equals(email, user.email) && Objects.equals(hashedPassword, user.hashedPassword) &&
                Objects.equals(userName, user.userName) && Objects.equals(profileID, user.profileID) &&
                Objects.equals(function, user.function) && Objects.equals(photo, user.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }


}
