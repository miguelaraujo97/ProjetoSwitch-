package switchisep.project.domain.domainservices;

import org.junit.jupiter.api.*;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.userstory.UserStoryStatus;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.error_handling.BusinessRulesException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UsToSprintBacklogDomainServiceTest {


    @Test
    void fail_SprintStatusFinished() {
        //arrange
        Project project = mock(Project.class);
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = mock(Sprint.class);

        UserStoryStatus userStoryStatus = UserStoryStatus.
                createUserStoryStatus(
                        UserStoryStatus.UserStoryStatusEnum.FINISHED);
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(
                SprintStatus.SprintStatusEnum.FINISHED);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        UsToSprintBacklogDomainService usToSprintBacklogDomainService =
                new UsToSprintBacklogDomainService();
        //Act
        BusinessRulesException exception =
                assertThrows(BusinessRulesException.class, () -> {
                    usToSprintBacklogDomainService.addToSprintBackLog(
                            project, userStory, sprint
                    );
                });
        //assert
        assertEquals("Sprint has already " +
                "finished", exception.getMessage());

    }

    //    @Test
//    void fail_SprintStatusStarted() {
//        //arrange
//        Project project = mock(Project.class);
//        UserStory userStory = mock(UserStory.class);
//        Sprint sprint = mock(Sprint.class);
//
//        UserStoryStatus userStoryStatus = UserStoryStatus.
//                createUserStoryStatus(
//                        UserStoryStatus.UserStoryStatusEnum.FINISHED);
//        SprintStatus sprintStatus = SprintStatus.createSprintStatus(
//                SprintStatus.SprintStatusEnum.STARTED);
//
//        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
//        when(userStory.getStatus()).thenReturn(userStoryStatus);
//        UsToSprintBacklogDomainService usToSprintBacklogDomainService =
//                new UsToSprintBacklogDomainService(project,
//                        userStory, sprint);
//        //Act
//        BusinessRulesException exception =
//                assertThrows(BusinessRulesException.class, () -> {
//                    usToSprintBacklogDomainService.addToSprintBackLog();
//                });
//        //assert
//        assertEquals("Sprint has already " +
//                "started", exception.getMessage());
//
//    }
    @Test
    void fail_UserStoryStatusFinished() {
        //arrange
        Project project = mock(Project.class);
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = mock(Sprint.class);

        UserStoryStatus userStoryStatus = UserStoryStatus.
                createUserStoryStatus(
                        UserStoryStatus.UserStoryStatusEnum.FINISHED);
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(
                SprintStatus.SprintStatusEnum.NOT_STARTED);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        UsToSprintBacklogDomainService usToSprintBacklogDomainService =
                new UsToSprintBacklogDomainService();
        //Act
        BusinessRulesException exception =
                assertThrows(BusinessRulesException.class, () -> {
                    usToSprintBacklogDomainService.addToSprintBackLog(
                            project, userStory, sprint
                    );
                });
        //assert
        assertEquals("User story has already " +
                "finished", exception.getMessage());

    }

    @Test
    void fail_UserStoryDoenstBelongToProject() {
        //arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        ProjectCode projectCode1 = mock(ProjectCode.class);
        Project project = mock(Project.class);
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = mock(Sprint.class);

        UserStoryStatus userStoryStatus = UserStoryStatus.
                createUserStoryStatus(
                        UserStoryStatus.UserStoryStatusEnum.PLANNED);
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(
                SprintStatus.SprintStatusEnum.NOT_STARTED);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        when(userStory.getProjectCode()).thenReturn(projectCode);
        when(project.getProjectCode()).thenReturn(projectCode1);

        UsToSprintBacklogDomainService usToSprintBacklogDomainService =
                new UsToSprintBacklogDomainService();

        //Act
        BusinessRulesException exception =
                assertThrows(BusinessRulesException.class, () -> {
                    usToSprintBacklogDomainService.addToSprintBackLog(
                            project,userStory,sprint
                    );
                });
        //assert
        assertEquals("User story doesnt belong to the project",
                exception.getMessage());

    }

    @Test
    void fail_SprintDoesntBelongToProject() {
        //arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        ProjectCode projectCode1 = mock(ProjectCode.class);
        Project project = mock(Project.class);
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = mock(Sprint.class);

        UserStoryStatus userStoryStatus = UserStoryStatus.
                createUserStoryStatus(
                        UserStoryStatus.UserStoryStatusEnum.PLANNED);
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(
                SprintStatus.SprintStatusEnum.NOT_STARTED);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        when(userStory.getProjectCode()).thenReturn(projectCode);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(sprint.getProjectCode()).thenReturn(projectCode1);

        UsToSprintBacklogDomainService usToSprintBacklogDomainService =
                new UsToSprintBacklogDomainService();

        //Act
        BusinessRulesException exception =
                assertThrows(BusinessRulesException.class, () -> {
                    usToSprintBacklogDomainService.addToSprintBackLog(
                            project,userStory,sprint
                    );
                });
        //assert
        assertEquals("Sprint doesnt belong to the project",
                exception.getMessage());

    }

    @Test
    void success_() {
        //arrange
        ProjectCode projectCode = mock(ProjectCode.class);
        ProjectCode projectCode1 = mock(ProjectCode.class);
        Project project = mock(Project.class);
        UserStory userStory = mock(UserStory.class);
        Sprint sprint = mock(Sprint.class);

        UserStoryStatus userStoryStatus = UserStoryStatus.
                createUserStoryStatus(
                        UserStoryStatus.UserStoryStatusEnum.PLANNED);
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(
                SprintStatus.SprintStatusEnum.NOT_STARTED);

        when(sprint.getSprintStatus()).thenReturn(sprintStatus);
        when(userStory.getStatus()).thenReturn(userStoryStatus);
        when(userStory.getProjectCode()).thenReturn(projectCode);
        when(project.getProjectCode()).thenReturn(projectCode);
        when(sprint.getProjectCode()).thenReturn(projectCode);

        UsToSprintBacklogDomainService usToSprintBacklogDomainService =
                new UsToSprintBacklogDomainService();

        //Act
        Optional<UserStory> userStoryResult =
                usToSprintBacklogDomainService.addToSprintBackLog(
                        project,userStory,sprint);
        //assert
        assertEquals(Optional.of(userStory), userStoryResult);

    }


    @Test
    void fail_SprintStatusFinished_IT() {
        //arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");

        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();

        Description description = Description.createDescription("teste");
        UserStoryID userStoryID = UserStoryID.createUserStoryID();
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        Priority priority = Priority.createPriority(1);
        UserStoryID parentUS = UserStoryID.createUserStoryIdWithString("0");

        UserStory userStory = new UserStory.Builder(
                userStoryID, projectCode,
                description).
                effort(effortEstimate).priority(priority).
                statusPlanned().
                parentUS(parentUS).build();


        SprintID sprintID = SprintID.createSprintID("S001");
        LocalDate startDate = LocalDate.of(2023, 10, 01);
        LocalDate endDate = LocalDate.of(2024, 10, 01);
        TimePeriod plannedTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        SprintStatus sprintStatus =
                SprintStatus.createSprintStatus(
                        SprintStatus.SprintStatusEnum.FINISHED);
        Sprint sprint = new Sprint(sprintID, projectCode,
                plannedTimePeriod, sprintStatus);

        UsToSprintBacklogDomainService usToSprintBacklogDomainService =
                new UsToSprintBacklogDomainService();
        //Act
        BusinessRulesException exception =
                assertThrows(BusinessRulesException.class, () -> {
                    usToSprintBacklogDomainService.addToSprintBackLog(
                            project,userStory,sprint
                    );
                });
        //assert
        assertEquals("Sprint has already " +
                "finished", exception.getMessage());

    }


}