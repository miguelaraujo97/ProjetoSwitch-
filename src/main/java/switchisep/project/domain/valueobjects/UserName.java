package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Miguel Araújo
 * UserName Value Object to be used in User Class.
 * A user name can only contain letters - no digits or special characters are allowed.
 */
@Component
public class UserName implements ValueObject<UserName>, Serializable {

    private String userName;

    private UserName(String userName) {
        this.userName = userName;
    }

    public UserName() {
    }

    public String getUserName() {
        return userName;
    }

    public static UserName createUserName(String userName) {
        if (!stringIsNotEmptyOrNull(userName)) {
            throw new IllegalStateException("Invalid user name string: cannot be empty or null");
        } else if (!isStringLettersOnly(userName)) {
            throw new IllegalArgumentException("Invalid user name string: cannot have digits or special characters");
        }
        return new UserName(userName);
    }


    /**
     * @param userName
     * @return true if all chars are letters
     * @author Miguel Araújo
     * Method that goes through all chars in String and returns true if all of them are letters.
     */
    private static boolean isStringLettersOnly(String userName) {
        boolean allCharsAreLetters = true;
        boolean auxBoolean = true;

        //Regex to remove white spaces
        String userNameWithoutWhiteSpaces = userName.replaceAll("\\s", "");


        for (char c : userNameWithoutWhiteSpaces.toCharArray()) {
            auxBoolean = Character.isLetter(c);
            if (!auxBoolean) {
                allCharsAreLetters = false;
            }
        }

        return allCharsAreLetters;
    }

    /**
     * @param userName
     * @return true if string is not null and is not empty.
     * @author Miguel Araújo
     */
    private static boolean stringIsNotEmptyOrNull(String userName) {
        return userName != null && !userName.trim().isEmpty();
    }

    @Override
    public boolean sameValueAs(UserName other) {
        return other != null && this.userName.equals(other.userName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserName)) return false;
        UserName other = (UserName) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
