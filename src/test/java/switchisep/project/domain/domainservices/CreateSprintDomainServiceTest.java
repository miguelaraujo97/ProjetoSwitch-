package switchisep.project.domain.domainservices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.SprintCreationInfo;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.sprint.SprintFactoryInterface;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.error_handling.BusinessRulesException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateSprintDomainServiceTest {

    @InjectMocks
    CreateSprintDomainService createSprintDomainService;

    @Test
    void generateNextSprintID_FirstSprint(){
        //Arrange
        List<Sprint> sprintList = new ArrayList<>();

        SprintID expected = SprintID.createSprintID("S001PA123");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        //Act
        SprintID result = createSprintDomainService.generateNextSprintID(sprintList, projectCode);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void generateNextSprintID_10thSprint(){
        //Arrange
        List<Sprint> sprintList = mock(List.class);

        when(sprintList.size()).thenReturn(9);

        SprintID expected = SprintID.createSprintID("S010PA123");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        //Act
        SprintID result = createSprintDomainService.generateNextSprintID(sprintList, projectCode);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void generateNextSprintID_100thSprint(){
        //Arrange
        List<Sprint> sprintList = mock(List.class);

        when(sprintList.size()).thenReturn(99);

        SprintID expected = SprintID.createSprintID("S100PA123");
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        //Act
        SprintID result = createSprintDomainService.generateNextSprintID(sprintList, projectCode);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void timePeriodOverlappingDetection_ReturnsFalse_NoOverlap(){
        //Arrange
        List<Sprint> sprintList = new ArrayList<>();

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        //Relative Dates
        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 10, 20);

        //ProjectID creation
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        //Sprint 1 arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate, endDate);
        Sprint sprint1 = new Sprint(sprintID1, projectCode, timePeriod1, sprintStatus);

        //Sprint 2 arrange
        SprintID sprintID2 = SprintID.createSprintID("S002");
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate.plusMonths(1), endDate.plusMonths(1));
        Sprint sprint2 = new Sprint(sprintID2,projectCode, timePeriod2, sprintStatus);

        //Sprint 3 arrange
        SprintID sprintID3 = SprintID.createSprintID("S003");
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate.plusMonths(2), endDate.plusMonths(2));
        Sprint sprint3 = new Sprint(sprintID3, projectCode, timePeriod3, sprintStatus);

        //Add sprints to sprintList
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        //Act
        boolean result = createSprintDomainService.sprintDatesOverlapWithOtherSprints(startDate.plusMonths(3),
                endDate.plusMonths(3), sprintList);

        //Assert
        assertFalse(result);
    }

    @Test
    void timePeriodOverlappingDetection_ReturnsTrue_OverlapFirstCondition(){
        //Arrange
        List<Sprint> sprintList = new ArrayList<>();

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        //Relative Dates
        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 10, 20);

        //ProjectID creation
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        //Sprint 1 arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate, endDate);
        Sprint sprint1 = new Sprint(sprintID1, projectCode, timePeriod1, sprintStatus);

        //Sprint 2 arrange
        SprintID sprintID2 = SprintID.createSprintID("S002");
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate.plusMonths(1), endDate.plusMonths(1));
        Sprint sprint2 = new Sprint(sprintID2, projectCode, timePeriod2, sprintStatus);

        //Sprint 3 arrange
        SprintID sprintID3 = SprintID.createSprintID("S003");
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate.plusMonths(2), endDate.plusMonths(2));
        Sprint sprint3 = new Sprint(sprintID3, projectCode, timePeriod3, sprintStatus);

        //Add sprints to sprintList
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        //Act
        boolean result = createSprintDomainService.sprintDatesOverlapWithOtherSprints(startDate.plusDays(1),
                endDate, sprintList);

        //Assert
        assertTrue(result);
    }

    @Test
    void timePeriodOverlappingDetection_ReturnsTrue_OverlapSecondCondition(){
        //Arrange
        List<Sprint> sprintList = new ArrayList<>();

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        //Relative Dates
        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 10, 20);

        //ProjectID creation
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        //Sprint 1 arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate, endDate);
        Sprint sprint1 = new Sprint(sprintID1, projectCode, timePeriod1, sprintStatus);

        //Sprint 2 arrange
        SprintID sprintID2 = SprintID.createSprintID("S002");
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate.plusMonths(1), endDate.plusMonths(1));
        Sprint sprint2 = new Sprint(sprintID2, projectCode, timePeriod2, sprintStatus);

        //Sprint 3 arrange
        SprintID sprintID3 = SprintID.createSprintID("S003");
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate.plusMonths(2), endDate.plusMonths(2));
        Sprint sprint3 = new Sprint(sprintID3, projectCode, timePeriod3, sprintStatus);

        //Add sprints to sprintList
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        //Act
        boolean result = createSprintDomainService.sprintDatesOverlapWithOtherSprints(startDate,
                endDate.minusDays(1), sprintList);

        //Assert
        assertTrue(result);
    }

    @Test
    void timePeriodOverlappingDetection_ReturnsTrue_OverlapFourthCondition(){
        //Arrange
        List<Sprint> sprintList = new ArrayList<>();

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        //Relative Dates
        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 10, 20);

        //ProjectID creation
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        //Sprint 1 arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate, endDate);
        Sprint sprint1 = new Sprint(sprintID1, projectCode, timePeriod1, sprintStatus);

        //Sprint 2 arrange
        SprintID sprintID2 = SprintID.createSprintID("S002");
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate.plusMonths(1), endDate.plusMonths(1));
        Sprint sprint2 = new Sprint(sprintID2, projectCode, timePeriod2, sprintStatus);

        //Sprint 3 arrange
        SprintID sprintID3 = SprintID.createSprintID("S003");
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate.plusMonths(2), endDate.plusMonths(2));
        Sprint sprint3 = new Sprint(sprintID3, projectCode, timePeriod3, sprintStatus);

        //Add sprints to sprintList
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        //Act
        boolean result = createSprintDomainService.sprintDatesOverlapWithOtherSprints(startDate,
                endDate, sprintList);

        //Assert
        assertTrue(result);
    }

    @Test
    void timePeriodOverlappingDetection_ReturnsTrue_OverlapFifthCondition(){
        //Arrange
        List<Sprint> sprintList = new ArrayList<>();

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        //Relative Dates
        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 10, 20);

        //ProjectID creation
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        //Sprint 1 arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate, endDate);
        Sprint sprint1 = new Sprint(sprintID1, projectCode, timePeriod1, sprintStatus);

        //Sprint 2 arrange
        SprintID sprintID2 = SprintID.createSprintID("S002");
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate.plusMonths(1), endDate.plusMonths(1));
        Sprint sprint2 = new Sprint(sprintID2, projectCode, timePeriod2, sprintStatus);

        //Sprint 3 arrange
        SprintID sprintID3 = SprintID.createSprintID("S003");
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate.plusMonths(2), endDate.plusMonths(2));
        Sprint sprint3 = new Sprint(sprintID3, projectCode, timePeriod3, sprintStatus);

        //Add sprints to sprintList
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        //Act
        boolean result = createSprintDomainService.sprintDatesOverlapWithOtherSprints(endDate,
                endDate.plusDays(1), sprintList);

        //Assert
        assertTrue(result);
    }

    @Test
    void sortSprintOrdersUponSprintCreation_AlreadyOrderedList(){
        //Arrange
        List<Sprint> sprintList = new ArrayList<>();

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        //Relative Dates
        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 10, 20);

        //ProjectID creation
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        //Sprint 1 arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate, endDate);
        Sprint sprint1 = new Sprint(sprintID1, projectCode, timePeriod1, sprintStatus);

        //Sprint 2 arrange
        SprintID sprintID2 = SprintID.createSprintID("S002");
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate.plusMonths(1), endDate.plusMonths(1));
        Sprint sprint2 = new Sprint(sprintID2, projectCode, timePeriod2, sprintStatus);

        //Sprint 3 arrange
        SprintID sprintID3 = SprintID.createSprintID("S003");
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate.plusMonths(2), endDate.plusMonths(2));
        Sprint sprint3 = new Sprint(sprintID3, projectCode, timePeriod3, sprintStatus);

        //Add sprints to sprintList
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        //New Sprint (4) arrange
        SprintID sprintID4 = SprintID.createSprintID("S003");
        TimePeriod timePeriod4 = TimePeriod.createTimePeriod(startDate.plusMonths(3), endDate.plusMonths(3));
        Sprint sprint4 = new Sprint(sprintID1, projectCode, timePeriod4, sprintStatus);

        //SprintOrder creation
        SprintOrder sprintOrder1 = SprintOrder.createSprintOrder(1);
        SprintOrder sprintOrder2 = SprintOrder.createSprintOrder(2);
        SprintOrder sprintOrder3 = SprintOrder.createSprintOrder(3);
        SprintOrder sprintOrder4 = SprintOrder.createSprintOrder(4);

        //Act
        Sprint sprintResult = createSprintDomainService.sortSprintOrdersUponSprintCreation(sprint4, sprintList);

        //Assert
        assertEquals(sprint4, sprintResult);
        assertEquals(sprint1.getSprintOrder(), sprintOrder1);
        assertEquals(sprint2.getSprintOrder(), sprintOrder2);
        assertEquals(sprint3.getSprintOrder(), sprintOrder3);
        assertEquals(sprint4.getSprintOrder(), sprintOrder4);
    }

    @Test
    void sortSprintOrdersUponSprintCreation_NotOrderedList(){
        //Arrange
        List<Sprint> sprintList = new ArrayList<>();

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        //Relative Dates
        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 10, 20);

        //ProjectID creation
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");


        //Sprint 1 arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate.plusMonths(4), endDate.plusMonths(4));
        Sprint sprint1 = new Sprint(sprintID1, projectCode, timePeriod1, sprintStatus);

        //Sprint 2 arrange
        SprintID sprintID2 = SprintID.createSprintID("S002");
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate.plusMonths(3), endDate.plusMonths(3));
        Sprint sprint2 = new Sprint(sprintID2, projectCode, timePeriod2, sprintStatus);

        //Sprint 3 arrange
        SprintID sprintID3 = SprintID.createSprintID("S003");
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate.plusMonths(2), endDate.plusMonths(2));
        Sprint sprint3 = new Sprint(sprintID3, projectCode, timePeriod3, sprintStatus);

        //Add sprints to sprintList
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        //New Sprint (4) arrange
        SprintID sprintID4 = SprintID.createSprintID("S003");
        TimePeriod timePeriod4 = TimePeriod.createTimePeriod(startDate, endDate);
        Sprint sprint4 = new Sprint(sprintID4, projectCode, timePeriod4, sprintStatus);

        //SprintOrder creation
        SprintOrder sprintOrder1 = SprintOrder.createSprintOrder(1);
        SprintOrder sprintOrder2 = SprintOrder.createSprintOrder(2);
        SprintOrder sprintOrder3 = SprintOrder.createSprintOrder(3);
        SprintOrder sprintOrder4 = SprintOrder.createSprintOrder(4);

        //Act
        Sprint sprintResult = createSprintDomainService.sortSprintOrdersUponSprintCreation(sprint4, sprintList);

        //Assert
        assertEquals(sprint4, sprintResult);
        assertEquals(sprint1.getSprintOrder(), sprintOrder4);
        assertEquals(sprint2.getSprintOrder(), sprintOrder3);
        assertEquals(sprint3.getSprintOrder(), sprintOrder2);
        assertEquals(sprint4.getSprintOrder(), sprintOrder1);
    }

    @Test
    void createSprintTest_FailureScenario_SprintIsNotCreatedBecauseOfOtherSprintOverlap(){
        //Arrange

        //Project creation
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("A123");
        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);

        Project project = new Project.Builder(projectCode, ProjectName.createProjectName("Name"),
                projectCustomer).projectSprintDuration(sprintDuration).build();

        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        sprintCreationInfo.plannedStartDate = LocalDate.of(2022, 10, 10);

        List<Sprint> sprintList = new ArrayList<>();

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        SprintFactoryInterface sprintFactoryInterfaceDouble = mock(SprintFactoryInterface.class);

        //Relative Dates
        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 10, 20);

        //Sprint 1 arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate, endDate);
        Sprint sprint1 = new Sprint(sprintID1, projectCode, timePeriod1, sprintStatus);

        //Sprint 2 arrange
        SprintID sprintID2 = SprintID.createSprintID("S002");
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate.plusMonths(1), endDate.plusMonths(1));
        Sprint sprint2 = new Sprint(sprintID2, projectCode, timePeriod2, sprintStatus);

        //Sprint 3 arrange
        SprintID sprintID3 = SprintID.createSprintID("S003");
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate.plusMonths(2), endDate.plusMonths(2));
        Sprint sprint3 = new Sprint(sprintID3, projectCode, timePeriod3, sprintStatus);

        //Add sprints to sprintList
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        //Act
        assertThrows(BusinessRulesException.class, () ->
                {createSprintDomainService.createSprint(sprintCreationInfo, project,
                sprintList, sprintFactoryInterfaceDouble);});

    }

    @Test
    void createSprintTest_FailureScenario_SprintIsNotCreatedBecauseOfProjectDates(){
        //Arrange

        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        sprintCreationInfo.plannedStartDate = LocalDate.of(2022, 10, 10);

        //Project creation
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);

        TimePeriod timePeriodProject = TimePeriod.createTimePeriod(sprintCreationInfo.plannedStartDate.minusDays(1),
                sprintCreationInfo.plannedStartDate.plusMonths(4));


        Project project = new Project.Builder(projectCode, ProjectName.createProjectName("Name"),
                projectCustomer).projectSprintDuration(sprintDuration)
                .projectTimePeriod(timePeriodProject).build();

        List<Sprint> sprintList = new ArrayList<>();

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        SprintFactoryInterface sprintFactoryInterfaceDouble = mock(SprintFactoryInterface.class);

        //Relative Dates
        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 10, 20);

        //Sprint 1 arrange
        SprintID sprintID1 = SprintID.createSprintID("S001");
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate, endDate);
        Sprint sprint1 = new Sprint(sprintID1, projectCode, timePeriod1, sprintStatus);

        //Sprint 2 arrange
        SprintID sprintID2 = SprintID.createSprintID("S002");
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate.plusMonths(1), endDate.plusMonths(1));
        Sprint sprint2 = new Sprint(sprintID2, projectCode, timePeriod2, sprintStatus);

        //Sprint 3 arrange
        SprintID sprintID3 = SprintID.createSprintID("S003");
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate.plusMonths(2), endDate.plusMonths(2));
        Sprint sprint3 = new Sprint(sprintID3, projectCode, timePeriod3, sprintStatus);

        //Add sprints to sprintList
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        //Act
        assertThrows(BusinessRulesException.class, () ->
        {createSprintDomainService.createSprint(sprintCreationInfo, project,
                sprintList, sprintFactoryInterfaceDouble);});
    }

    @Test
    void createSprintTest_SuccessScenario_SprintIsCreatedWithSprintOrder4(){
        //Arrange

        //Project creation
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);

        Project project = new Project.Builder(projectCode, ProjectName.createProjectName("Name"),
                projectCustomer).projectSprintDuration(sprintDuration).build();

        SprintCreationInfo sprintCreationInfo = new SprintCreationInfo();
        sprintCreationInfo.plannedStartDate = LocalDate.of(2023, 10, 10);

        List<Sprint> sprintList = new ArrayList<>();

        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        SprintFactoryInterface sprintFactoryInterfaceDouble = mock(SprintFactoryInterface.class);

        //Relative Dates
        LocalDate startDate = LocalDate.of(2022, 10, 10);
        LocalDate endDate = LocalDate.of(2022, 10, 20);

        //Sprint 1 arrange
        SprintID sprintID1 = SprintID.createSprintID("S001PA123");
        TimePeriod timePeriod1 = TimePeriod.createTimePeriod(startDate, endDate);
        Sprint sprint1 = new Sprint(sprintID1, projectCode, timePeriod1, sprintStatus);

        //Sprint 2 arrange
        SprintID sprintID2 = SprintID.createSprintID("S002PA123");
        TimePeriod timePeriod2 = TimePeriod.createTimePeriod(startDate.plusMonths(1), endDate.plusMonths(1));
        Sprint sprint2 = new Sprint(sprintID2, projectCode, timePeriod2, sprintStatus);

        //Sprint 3 arrange
        SprintID sprintID3 = SprintID.createSprintID("S003PA123");
        TimePeriod timePeriod3 = TimePeriod.createTimePeriod(startDate.plusMonths(2), endDate.plusMonths(2));
        Sprint sprint3 = new Sprint(sprintID3, projectCode, timePeriod3, sprintStatus);

        //Add sprints to sprintList
        sprintList.add(sprint1);
        sprintList.add(sprint2);
        sprintList.add(sprint3);

        //Expected sprint 4 arrange
        SprintID sprintID4 = SprintID.createSprintID("S004PA123");
        TimePeriod timePeriod4 = TimePeriod.createTimePeriod(sprintCreationInfo.plannedStartDate,
                sprintCreationInfo.plannedStartDate.plusWeeks(1));
        SprintOrder sprintOrder4 = SprintOrder.createSprintOrder(4);
        Sprint sprintExpected = new Sprint(sprintID4, projectCode, timePeriod4, sprintStatus);

        sprintExpected.swapSprintOrder(sprintOrder4);

        when(sprintFactoryInterfaceDouble.createSprint(sprintID4, projectCode,
                timePeriod4, sprintStatus)).thenReturn(sprintExpected);
        //Act
        Sprint result = createSprintDomainService.createSprint(sprintCreationInfo, project,
                sprintList, sprintFactoryInterfaceDouble);


        //Assert
        assertEquals(sprintExpected, result);
    }

}