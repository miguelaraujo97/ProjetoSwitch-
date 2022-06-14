package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class UserID implements ValueObject<UserID> {
    private int userID;

    private UserID(int userID) {
        this.userID = userID;
    }

    public static UserID createUserID(int userID) {

        if (userID <0 ) {
            throw new IllegalArgumentException("Email can't be null");
        } else
            return new UserID(userID);
    }

    public UserID() {
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public boolean sameValueAs(UserID other) {
        return other != null && this.userID==other.userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserID)) return false;
        UserID other = (UserID) o;
        return sameValueAs(other);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID);
    }
}
