package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ProjectActivityStatusDTO;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.userstory.UserStoryStatus;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.UserStoryID;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.IUserStoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViewActivitiesStatusServiceTest {

    @InjectMocks
    ViewActivitiesStatusService viewActivitiesStatusService;

    @Mock
    IUserStoryRepository userStoryRepository;

    @Mock
    ProjectRepository projectRepository;


    @Test
    void getAllProjectActivities_success() {

        //Arrange



        String projectCode = "A123";

        ProjectCode projectCodeVO = ProjectCode.createProjectCode(projectCode);

        //---------------------------------------------------------------------------------------------------//
        UserStory us1 = mock(UserStory.class);
        UserStory us2 = mock(UserStory.class);
        UserStory us3 = mock(UserStory.class);

        List<UserStory> projectUserStoriesList = new ArrayList<>();

        projectUserStoriesList.add(us1);
        projectUserStoriesList.add(us2);
        projectUserStoriesList.add(us3);

        //---------------------------------------------------------------------------------------------------//

        UserStoryID VO_ID_US1 = UserStoryID.createUserStoryID();
        UserStoryID VO_ID_US2 = UserStoryID.createUserStoryID();
        UserStoryID VO_ID_US3 = UserStoryID.createUserStoryID();

        Description VO_DESC_US1 = Description.createDescription("us1 desc");
        Description VO_DESC_US2 = Description.createDescription("us2 desc");
        Description VO_DESC_US3 = Description.createDescription("us3 desc");

        UserStoryStatus VO_STATUS_US1 = UserStoryStatus.createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.PLANNED);
        UserStoryStatus VO_STATUS_US2 = UserStoryStatus.createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.CANCELLED);
        UserStoryStatus VO_STATUS_US3 = UserStoryStatus.createUserStoryStatus(UserStoryStatus.UserStoryStatusEnum.FINISHED);
        //---------------------------------------------------------------------------------------------------//

        when(us1.getUserStoryID()).thenReturn(VO_ID_US1);
        when(us2.getUserStoryID()).thenReturn(VO_ID_US2);
        when(us3.getUserStoryID()).thenReturn(VO_ID_US3);

        when(us1.getUserStoryDescription()).thenReturn(VO_DESC_US1);
        when(us2.getUserStoryDescription()).thenReturn(VO_DESC_US2);
        when(us3.getUserStoryDescription()).thenReturn(VO_DESC_US3);

        when(us1.getStatus()).thenReturn(VO_STATUS_US1);
        when(us2.getStatus()).thenReturn(VO_STATUS_US2);
        when(us3.getStatus()).thenReturn(VO_STATUS_US3);

        //---------------------------------------------------------------------------------------------------//

        when(projectRepository.existsProjectByProjectCode(projectCodeVO)).thenReturn(true);

        when(userStoryRepository.findAllByProjectCode(projectCodeVO)).thenReturn(projectUserStoriesList);

        //---------------------------------------------------------------------------------------------------//

        ProjectActivityStatusDTO prjActivityStatusDTO_1 = new ProjectActivityStatusDTO();
        ProjectActivityStatusDTO prjActivityStatusDTO_2 = new ProjectActivityStatusDTO();
        ProjectActivityStatusDTO prjActivityStatusDTO_3 = new ProjectActivityStatusDTO();

        prjActivityStatusDTO_1.activityID = VO_ID_US1.getUserStoryID();
        prjActivityStatusDTO_2.activityID = VO_ID_US2.getUserStoryID();
        prjActivityStatusDTO_3.activityID = VO_ID_US3.getUserStoryID();

        prjActivityStatusDTO_1.activityDescription = VO_DESC_US1.getDescription();
        prjActivityStatusDTO_2.activityDescription = VO_DESC_US2.getDescription();
        prjActivityStatusDTO_3.activityDescription = VO_DESC_US3.getDescription();

        prjActivityStatusDTO_1.activityStatus = VO_STATUS_US1.toString();
        prjActivityStatusDTO_2.activityStatus = VO_STATUS_US2.toString();
        prjActivityStatusDTO_3.activityStatus = VO_STATUS_US3.toString();


        //---------------------------------------------------------------------------------------------------//

        List<ProjectActivityStatusDTO> projectActivityStatusDTOList_expected = new ArrayList<>();

        projectActivityStatusDTOList_expected.add(prjActivityStatusDTO_1);
        projectActivityStatusDTOList_expected.add(prjActivityStatusDTO_2);
        projectActivityStatusDTOList_expected.add(prjActivityStatusDTO_3);


        Optional expected =  Optional.of(projectActivityStatusDTOList_expected);

        //---------------------------------------------------------------------------------------------------//


        //Act
        Optional<List<ProjectActivityStatusDTO>> actual = viewActivitiesStatusService.getAllProjectActivities(projectCode);

        //ASSERT
        assertEquals(expected, actual);

    }

    @Test
    void getAllProjectActivities_fail_projectNotFound() {

        //Arrange
        String projectCode = "A123";

        ProjectCode projectCodeVO = ProjectCode.createProjectCode(projectCode);

        when(projectRepository.existsProjectByProjectCode(projectCodeVO)).thenReturn(false);


        Optional expected =  Optional.empty();

        //Act
        Optional<List<ProjectActivityStatusDTO>> actual = viewActivitiesStatusService.getAllProjectActivities(projectCode);

        //Assert
        assertEquals(expected, actual);

    }
}