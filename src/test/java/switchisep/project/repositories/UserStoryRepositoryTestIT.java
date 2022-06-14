package switchisep.project.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.domain.domainservices.CreateUserStoryDomainService;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.userstory.UserStoryStatus;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserStoryRepositoryTestIT {
    @Autowired
    UserStoryRepository userStoryRepository;

    @Autowired
    UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;
    @Autowired
    CreateUserStoryDomainService createUserStoryDomainService;

    @Test
    void createSuccess(){
        //arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A125");
        TimePeriod timePeriod = TimePeriod.createTimePeriod(LocalDate.of(2022, 01, 01),
                LocalDate.of(2023, 01, 01));
        UserStoryID userStoryID = UserStoryID.
                createUserStoryIdWithString("1");
        Description description = Description.createDescription(
                "sadsdasdsadasdasdasdas");
        Priority priority = Priority.createPriority(1);
        SprintID sprintID = SprintID.createSprintID("1");
        UserStoryStatus userStoryStatus = UserStoryStatus.createUserStoryStatus(
                UserStoryStatus.UserStoryStatusEnum.PLANNED);
        EffortEstimate effortEstimate = EffortEstimate.createEffortEstimate(1);
        UserStory userStory = new UserStory.Builder(userStoryID,projectCode,
                description).parentUS(userStoryID).effort(effortEstimate).
                priority(priority).status(userStoryStatus).sprintID(sprintID).build();

        //act
        UserStory userStoryResult = userStoryRepository.save(userStory);
        Optional<UserStory> userStoryFromJPA = userStoryRepository.
                findByUserStoryID(userStoryID);
        UserStory userStory1 = userStoryFromJPA.get();
        Priority priority1 = Priority.createPriority(2);
        userStory1.setPriority(priority1);
        userStoryRepository.save(userStory1);
        //assert
        assertEquals(userStory,userStoryResult);
        //assertEquals(Optional.of(userStory),userStoryFromJPA);
    }





}