package switchisep.project.domain.valueobjects;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;

@Component
public class UserStoryID implements TaskContainerID, ValueObject<UserStoryID> {

    private final String ID;
    private static final String REF = "US";

    private UserStoryID() {
        this.ID = REF + "-" + UUID.randomUUID();
    }

    private UserStoryID(String id) {

        this.ID = id;
    }

    public static UserStoryID createUserStoryID() {

        return new UserStoryID();
    }

    public static UserStoryID createUserStoryIdWithString(String id) {

        return new UserStoryID(id);
    }

    public String getTaskContainerIDString() {

        return REF;
    }

    public String getUserStoryID() {

        return this.ID;
    }

    @Override
    public boolean sameValueAs(UserStoryID other) {
        return other != null && Objects.equals(this.ID,
                other.ID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStoryID that = (UserStoryID) o;
        return ((UserStoryID) o).ID.equals(that.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
