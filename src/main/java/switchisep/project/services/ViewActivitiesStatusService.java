package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.ProjectActivityStatusDTO;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.IUserStoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViewActivitiesStatusService {

    @Autowired
    IUserStoryRepository userStoryRepository;

    @Autowired
    ProjectRepository projectRepository;

    public Optional<List<ProjectActivityStatusDTO>> getAllProjectActivities(String projectCode) {

        ProjectCode projectCodeVO = ProjectCode.createProjectCode(projectCode);

        if (projectRepository.existsProjectByProjectCode(projectCodeVO)){

        List<ProjectActivityStatusDTO> projectActivitiesStatusDTOList = new ArrayList<>();

        List<UserStory> projectUserStoriesList = userStoryRepository.findAllByProjectCode(projectCodeVO);

        //TODO (NEXT SPRINT) we will need to get the Sprint related tasks and the US related tasks of
        // this project as well. The following method must be repeated (and adapted) for those tasks, OR have some If's
        // in here to sort between types of activities... need to study this through a bit more.

        for (UserStory userStory : projectUserStoriesList) {

            ProjectActivityStatusDTO projectActivityStatusDTO = new ProjectActivityStatusDTO();

            projectActivitiesStatusDTOList.add(projectActivityStatusDTO);

//            projectActivityStatusDTO.activityType = "User Story";
            projectActivityStatusDTO.activityID = userStory.getUserStoryID().getUserStoryID();
            projectActivityStatusDTO.activityDescription = userStory.getUserStoryDescription().getDescription();
            projectActivityStatusDTO.activityStatus = userStory.getStatus().toString();

        }
            return Optional.of(projectActivitiesStatusDTOList);

    }
        return Optional.empty();
}


}
