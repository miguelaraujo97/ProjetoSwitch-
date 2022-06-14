package switchisep.project.domain.userstory;


import org.springframework.stereotype.Component;
import switchisep.project.domain.valueobjects.ValueObject;

import java.util.Objects;

@Component
public class UserStoryStatus implements ValueObject<UserStoryStatus> {

    private String uSValueOfStatus;

    public UserStoryStatus() {
    }

    private UserStoryStatus(String uSValueOfStatus) {
        this.uSValueOfStatus = uSValueOfStatus;
    }

    /**
     * Enumeration of possible sprint statuses.
     */
    public enum UserStoryStatusEnum {
        PLANNED,
        RUNNING,
        FINISHED,
        CANCELLED
    }

    /**
     * Method that allows the creation of a new sprintStatus object.
     *
     * @param userStoryStatusEnum One of three possible values: NOT_STARTED, STARTED and FINISHED.
     * @return A new sprintStatus object, null if a valid value is not passed.
     */
    public static UserStoryStatus createUserStoryStatus(UserStoryStatusEnum userStoryStatusEnum) {
        for (UserStoryStatusEnum userStoryStatusEnum1 : UserStoryStatusEnum.values()) {
            if (userStoryStatusEnum1 == userStoryStatusEnum) {
                return new UserStoryStatus(userStoryStatusEnum1.toString());
            }
        }
        return null;
    }

    public String getuSValueOfStatus() {
        return uSValueOfStatus;
    }

    @Override
    public boolean sameValueAs(UserStoryStatus other) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStoryStatus that = (UserStoryStatus) o;
        return Objects.equals(uSValueOfStatus, that.uSValueOfStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uSValueOfStatus);
    }
}