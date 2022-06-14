package switchisep.project.datamodel.assemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.datamodel.UserStoryJpa;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserStoryDomainDataAssemblerTest {

    @Test
    void shouldConvertDomainToData() {

        // Arrange

        UserStoryDomainDataAssembler userStoryDomainDataAssembler = new UserStoryDomainDataAssembler();

        UserStoryID userStoryID = UserStoryID.createUserStoryID();

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        Description description = Description.createDescription("Teste");

        ParentUS parentUS = ParentUS.createParentUS(userStoryID);

        Priority priority = Priority.createPriority(1);

        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(0);

        SprintID sprintID = SprintID.createSprintID("S01");

        UserStory userStory = new UserStory.Builder(userStoryID, projectCode, description)
                .statusPlanned()
                .effort(effortEstimate)
                .priority(priority)
                .sprintID(sprintID)
                .parentUS(userStoryID).build();

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(),
                "A123", "Teste", parentUS.getUS().getUserStoryID(), "PLANNED",
                1, 0, "S01");

        // Act

        UserStoryJpa result = userStoryDomainDataAssembler.toData(userStory);

        // Assert

        assertEquals(userStoryJpa, result);
    }

    @Test
    void shouldConvertUserStoryToUserStoryJpa() {

        // Arrange

        UserStoryDomainDataAssembler userStoryDomainDataAssembler = new UserStoryDomainDataAssembler();

        UserStoryID userStoryID = UserStoryID.createUserStoryID();

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        Description description = Description.createDescription("Teste");

        ParentUS parentUS = ParentUS.createParentUS(userStoryID);

        Priority priority = Priority.createPriority(1);

        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(0);

        SprintID sprintID = SprintID.createSprintID("S01");

        UserStory userStory = new UserStory.Builder(userStoryID, projectCode, description)
                .statusPlanned()
                .effort(effortEstimate)
                .priority(priority)
                .sprintID(sprintID)
                .parentUS(userStoryID).build();

        UserStoryJpa userStoryJpa = new UserStoryJpa(userStoryID.getUserStoryID(),
                "A123", "Teste", parentUS.getUS().getUserStoryID(), "PLANNED",
                1, 0, "S01");

        // Act

        UserStory result = userStoryDomainDataAssembler.toDomain(userStoryJpa);

        // Assert

        assertEquals(userStory, result);

    }

}