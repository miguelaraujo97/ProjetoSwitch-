package switchisep.project.domain.factories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.*;


import static org.junit.jupiter.api.Assertions.*;

class UserStoryFactoryImplTest {

    UserStoryFactoryImpl userStoryFactory = new UserStoryFactoryImpl();
    ProjectCode code;
    ProjectName name;
    ProjectCustomer customer;


    @BeforeEach
    void setup() {
        code = ProjectCode.createProjectCode("a123");
        name = ProjectName.createProjectName("Test");
        customer = ProjectCustomer.createProjectCustomer("ISEP");
    }

    @Test
    void createUserStory_successesCase() {
        Project project = new Project.Builder(code, name, customer).build();

        Description description = Description.createDescription("Description");
        UserStoryID userStoryId = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("a123");
        Priority priority = Priority.createPriority(1);
        EffortEstimate effort = EffortEstimate.createEffortEstimate(1);
        SprintID sprintID = SprintID.createSprintID("Sp01");
        UserStoryID parentUS = UserStoryID.createUserStoryIdWithString("0");


        UserStory userStoryOne = userStoryFactory.createUserStory(description,
                userStoryId, projectCode, priority, effort, sprintID,parentUS);
        UserStory userStoryTwo = userStoryFactory.createUserStory(description,
                userStoryId, projectCode, priority, effort, sprintID,parentUS);

        assertEquals(userStoryOne, userStoryTwo);
    }

    @Test
    void createUserStory_DifferentDescription() {
        Project project = new Project.Builder(code, name, customer).build();

        Description description = Description.createDescription("Description");
        Description descriptionTwo = Description.createDescription(
                "escription");
        UserStoryID userStoryId = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("a123");
        Priority priority = Priority.createPriority(1);
        EffortEstimate effort = EffortEstimate.createEffortEstimate(1);
        SprintID sprintID = SprintID.createSprintID("Sp01");
        UserStoryID parentUS = UserStoryID.createUserStoryIdWithString("0");


        UserStory userStoryOne = userStoryFactory.createUserStory(description,
                userStoryId, projectCode, priority, effort, sprintID,parentUS);
        UserStory userStoryTwo =
                userStoryFactory.createUserStory(descriptionTwo,
                userStoryId, projectCode, priority, effort, sprintID,parentUS);

        assertNotEquals(userStoryOne, userStoryTwo);
    }


    @Test
    void createUserStory_DifferentEffort() {
        Project project = new Project.Builder(code, name, customer).build();

        Description description = Description.createDescription("Description");
        UserStoryID userStoryId = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("a123");
        Priority priority = Priority.createPriority(1);
        Priority priority2 = Priority.createPriority(2);
        SprintID sprintID = SprintID.createSprintID("Sp01");

        EffortEstimate effort = EffortEstimate.createEffortEstimate(1);
        UserStoryID parentUS = UserStoryID.createUserStoryIdWithString("0");

        UserStory userStoryOne = userStoryFactory.createUserStory(description,
                userStoryId, projectCode, priority, effort, sprintID,parentUS);
        UserStory userStoryTwo = userStoryFactory.createUserStory(description,
                userStoryId, projectCode, priority2, effort, sprintID,parentUS);

        assertNotEquals(userStoryOne, userStoryTwo);
    }

    @Test
    void createUserStory_DifferentPriority() {
        Project project = new Project.Builder(code, name, customer).build();

        Description description = Description.createDescription("Description");
        UserStoryID userStoryId = UserStoryID.createUserStoryID();
        ProjectCode projectCode = ProjectCode.createProjectCode("a123");
        Priority priority = Priority.createPriority(1);
        EffortEstimate effort = EffortEstimate.createEffortEstimate(1);
        EffortEstimate effort2 = EffortEstimate.createEffortEstimate(3);
        SprintID sprintID = SprintID.createSprintID("Sp01");
        UserStoryID parentUS = UserStoryID.createUserStoryIdWithString("0");

        UserStory userStoryOne = userStoryFactory.createUserStory(description,
                userStoryId, projectCode, priority, effort, sprintID,parentUS);
        UserStory userStoryTwo = userStoryFactory.createUserStory(description,
                userStoryId, projectCode, priority, effort2, sprintID,parentUS);

        assertNotEquals(userStoryOne, userStoryTwo);
    }
}