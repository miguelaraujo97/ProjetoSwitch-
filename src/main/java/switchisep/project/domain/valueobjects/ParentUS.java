package switchisep.project.domain.valueobjects;


import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ParentUS implements ValueObject<ParentUS>{

    private  UserStoryID userStoryId;

    private ParentUS(UserStoryID userStory) {
        this.userStoryId = userStory;
    }
    private ParentUS(){};

    /**
     * Static method to instantiate a Description VO
     *
     * @param userStoryID
     * @return
     */
    public static ParentUS createParentUS(UserStoryID userStoryID) {
//        if (Objects.isNull(userStoryID)) {
//            throw new IllegalArgumentException("UserStory can't" +
//                    " be null");
//        }
        return new ParentUS(userStoryID);
    }

    public UserStoryID getUS(){
        return this.userStoryId;
    }

    @Override
    public boolean sameValueAs(ParentUS other) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentUS parentUS = (ParentUS) o;
        return Objects.equals(userStoryId, parentUS.userStoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userStoryId);
    }

}
