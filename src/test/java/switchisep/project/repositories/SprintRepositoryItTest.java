package switchisep.project.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SprintRepositoryItTest {

    @Autowired
    SprintRepository sprintRepository;

//    @Test
//    void save() {
//        //arrange
//        SprintID sprintID = SprintID.createSprintID("1");
//        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
//        TimePeriod timePeriod = TimePeriod.createTimePeriod(
//                LocalDate.now(), LocalDate.now().plusWeeks(1));
//        SprintStatus sprintStatus = SprintStatus.createSprintStatus(STARTED);
//        SprintOrder sprintOrder = SprintOrder.createSprintOrder(1);
//        SprintFactory sprintFactory = new SprintFactory();
//        Sprint sprint = sprintFactory.createSprint(sprintID,
//                projectCode, timePeriod, sprintStatus);
//
//        //act
//        Sprint projectCode1 = sprintRepository.save(sprint);
//        //assert
//        assertEquals(projectCode, projectCode1);
//    }

    @Test
    void findAll() {
    }
}