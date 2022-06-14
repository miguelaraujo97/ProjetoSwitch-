package switchisep.project.repositories.interfaces;

import org.springframework.stereotype.Service;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.UserStoryID;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserStoryRepository {

    UserStory save(UserStory userStory);

    List<UserStory> findAllByProjectCode(ProjectCode projectCode);

    Optional<UserStory> findByUserStoryID(UserStoryID userStoryID);

    void deleteAll();

}
