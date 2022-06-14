package switchisep.project.datamodel;

import switchisep.project.domain.valueobjects.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserJpa implements Serializable {

    @EmbeddedId
    private UserID id;
    @Embedded
    private Email email;
    @Embedded
    private UserName userName;
    @Embedded
    private ProfileID profileID;
    @Embedded
    private Function function;
    @Embedded
    private Photo photo;
    @Embedded
    private PasswordHash hashedPassword;
    private boolean userStatus;


    public UserJpa() {
    }

    public UserJpa(UserID userID, UserName userName, Email email,
                   Function function, PasswordHash password,
                   Photo photo, ProfileID profileID,
                   boolean userStatus) {

        this.id = userID;
        this.userName = userName;
        this.email = email;
        this.function = function;
        this.profileID = profileID;
        this.hashedPassword = password;
        this.photo = photo;
        this.userStatus = userStatus;
    }

    public UserID getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public UserName getUserName() {
        return userName;
    }

    public ProfileID getProfileID() {
        return profileID;
    }

    public Function getFunction() {
        return function;
    }

    public Photo getPhoto() {
        return photo;
    }

    public PasswordHash getHashedPassword() {
        return hashedPassword;
    }

    public boolean getUserStatus() {
        return userStatus;
    }
}
