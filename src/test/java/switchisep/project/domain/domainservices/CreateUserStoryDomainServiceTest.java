package switchisep.project.domain.domainservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.factories.UserStoryFactory;
import switchisep.project.domain.valueobjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserStoryDomainServiceTest {

    @Mock
    UserStory userStory;

    @Mock
    UserStoryFactory userStoryFactory;

    @Test
    public void shouldReturnOptionalUserStory() {

        // Arrange

        CreateUserStoryDomainService createUserStoryDomainService =
                new CreateUserStoryDomainService(userStoryFactory);

        String descriptionString = "Teste";

        Description descriptionVO = Description.createDescription(descriptionString);

        UserStoryID userStoryIdVO = UserStoryID.createUserStoryID();

        ProjectCode projectCodeVO = ProjectCode.createProjectCode("A123");

        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(0);

        Priority priority = Priority.createPriority(1);

        SprintID sprintID = SprintID.createSprintID("-1");

        List<UserStory> productBacklog = new ArrayList<>();
        UserStoryID parentUS = UserStoryID.createUserStoryIdWithString("0");


        when(userStoryFactory.createUserStory
                (descriptionVO, userStoryIdVO, projectCodeVO,
                        priority, effortEstimate,sprintID,parentUS)).thenReturn(userStory);

        // Act

        Optional<UserStory> optionalResult =
                createUserStoryDomainService.createUserStory(descriptionString, projectCodeVO, productBacklog, userStoryIdVO);

        // Assert

        assertEquals(Optional.of(userStory), optionalResult);
    }

    @Test
    public void shouldReturnOptionalEmpty() {

        // Arrange

        CreateUserStoryDomainService createUserStoryDomainService =
                new CreateUserStoryDomainService(userStoryFactory);

        String descriptionString = "Teste";

        Description descriptionVO = Description.createDescription(descriptionString);

        UserStoryID userStoryIdVO = UserStoryID.createUserStoryID();

        ProjectCode projectCodeVO = ProjectCode.createProjectCode("A123");

        Priority priority = Priority.createPriority(1);

        List<UserStory> productBacklog = new ArrayList<>();

        productBacklog.add(userStory);

        when(userStory.getUserStoryDescription()).thenReturn(descriptionVO);

        // Act

        Optional<UserStory> optionalResult =
                createUserStoryDomainService.createUserStory(descriptionString, projectCodeVO, productBacklog, userStoryIdVO);

        // Assert

        assertEquals(Optional.empty(), optionalResult);
    }

}