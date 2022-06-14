package switchisep.project.domain.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.sprint.SprintFactoryInterface;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectTest {

    ProjectCode code;
    ProjectName name;
    ProjectCustomer customer;
    ProjectDescription description;
    ProjectBudget projectBudget;
    SprintDuration projectSprintDuration;
    ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints;
    ProjectStatus status;
    LocalDate startDate;
    LocalDate endDate;
    TimePeriod timePeriod;
    ProjectBusinessSector projectBusinessSector;

    @BeforeEach
    void setup() {
        code = ProjectCode.createProjectCode("a123");
        name = ProjectName.createProjectName("Test");
        customer = ProjectCustomer.createProjectCustomer("ISEP");
        description = ProjectDescription.createProjectDescription("CENAS");
        projectBudget = ProjectBudget.createBudget(10);
        projectSprintDuration = SprintDuration.createSprintDuration(10);
        projectNumberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(10);
        status = ProjectStatus.PLANNED;
        startDate = LocalDate.of(2022, 05, 10);
        endDate = LocalDate.of(2025, 06, 15);
        timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        projectBusinessSector = ProjectBusinessSector.createProjectBusinessSector("School");
    }

    @Test
    void nullCode() {

        //Arrange
        code = null;

        //Act & assert
        assertThrows(NullPointerException.class, () ->
                new Project.Builder(code, name, customer).build()
        );
    }

    @Test
    void nullName() {

        //Arrange
        ProjectCode code = ProjectCode.createProjectCode("A123");
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer("ISEP");
        name = null;

        //Act & assert
        assertThrows(NullPointerException.class, () ->
                new Project.Builder(code, name, customer).build()
        );
    }

    @Test
    void nullCustomer() {

        //Arrange
        ProjectCode code = ProjectCode.createProjectCode("A123");
        ProjectName name = ProjectName.createProjectName("Test");

        //Act & assert
        assertThrows(NullPointerException.class, () ->
                new Project.Builder(code, name, null).build()
        );

    }


    @Test
    void checkProjectExistTrue() {
        // Arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");

        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();


        boolean result = project.checkProjectExist(projectCode);


        //assert

        assertTrue(result);

    }

    @Test
    void checkProjectExistFalse() {
        // Arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectCode projectCode1 = ProjectCode.createProjectCode("A122");


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();

        boolean result = project.checkProjectExist(projectCode1);


        //assert

        assertFalse(result);

    }


    //---------------------------------------------------------------------------------------------------------------
    //Test To equals and hashcode

    @Test
    void checkEqualsAndHashCode() {
        //Arrange
        ProjectCode code = ProjectCode.createProjectCode("a123");
        ProjectCode code1 = ProjectCode.createProjectCode("a122");
        ProjectName name = ProjectName.createProjectName("Test");
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectDescription description = ProjectDescription.createProjectDescription("CENAS");
        ProjectBudget projectBudget = ProjectBudget.createBudget(10);
        SprintDuration projectSprintDuration = SprintDuration.createSprintDuration(10);
        ProjectNumberOfPlannedSprints projectNumberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(10);
        ProjectStatus status = ProjectStatus.PLANNED;
        LocalDate startDate = LocalDate.of(2022, 05, 10);
        LocalDate endDate = LocalDate.of(2025, 06, 15);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        ProjectBusinessSector projectBusinessSector = ProjectBusinessSector.createProjectBusinessSector("School");
        Description typologyDes = Description.createDescription("Insert typology");
        Object obj = new Object();

        //Act
        Project project = new Project.Builder(code, name, customer).projectTypologyDescription(typologyDes).projectStatus(status)
                .projectSprintDuration(projectSprintDuration).projectDescription(description).projectBudget(projectBudget)
                .projectNumberOfPlannedSprints(projectNumberOfPlannedSprints).projectTimePeriod(timePeriod).projectBusinessSector(projectBusinessSector).build();

        Project project1 = new Project.Builder(code, name, customer).projectTypologyDescription(typologyDes).projectStatus(status)
                .projectSprintDuration(projectSprintDuration).projectDescription(description).projectBudget(projectBudget)
                .projectNumberOfPlannedSprints(projectNumberOfPlannedSprints).projectTimePeriod(timePeriod).projectBusinessSector(projectBusinessSector).build();

        Project project2 = new Project.Builder(code1, name, customer).projectTypologyDescription(typologyDes).projectStatus(status)
                .projectSprintDuration(projectSprintDuration).projectDescription(description).projectBudget(projectBudget)
                .projectNumberOfPlannedSprints(projectNumberOfPlannedSprints).projectTimePeriod(timePeriod).projectBusinessSector(projectBusinessSector).build();
        // Assert
        assertEquals(project, project);
        assertEquals(project, project1);
        assertEquals(project.hashCode(), project.hashCode());
        assertNotEquals(project, project2);
        assertNotEquals(null, project);
        assertNotEquals(project, obj);
        assertNotEquals(project.hashCode(), project2.hashCode());
    }

    //----------------------------------------------------//---------------------------------------------------------//
    //Tests for getters


    @Test
    void getProjectCode() {
        // Arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();


        ProjectCode code1 = project.getProjectCode();


        //assert

        assertEquals(projectCode, code1);

    }

    @Test
    void getCode() {

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();


        ProjectCode result = project.getProjectCode();


        //assert

        assertEquals(projectCode, result);
    }

    @Test
    void getName() {
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();


        ProjectName result = project.getName();


        //assert

        assertEquals(projectName, result);
    }

    @Test
    void getProjectDescription() {

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectDescription projectDescription = ProjectDescription.createProjectDescription("Insert Description.");


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).projectDescription(projectDescription).build();


        ProjectDescription result = project.getProjectDescription();


        //assert

        assertEquals(projectDescription, result);
    }

    @Test
    void getProjectBudget() {
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectBudget budget = ProjectBudget.createBudget(0);


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();


        ProjectBudget result = project.getProjectBudget();


        //assert

        assertEquals(budget, result);
    }

    @Test
    void getTypologyDescription() {
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        Description typologyDescription = Description.createDescription("Insert typology.");


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();


        Description result = project.getTypologyDescription();


        //assert

        assertEquals(typologyDescription, result);
    }


    @Test
    void getCustomer() {

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");


        //act
        Project project = new Project.Builder(projectCode, projectName, customer).build();


        ProjectCustomer result = project.getCustomer();


        //assert

        assertEquals(customer, result);
    }

    @Test
    void getProjectSprintDuration() {
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        SprintDuration sprintDuration = SprintDuration.createSprintDuration(1);

        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();


        SprintDuration result = project.getProjectSprintDuration();


        //assert

        assertEquals(sprintDuration, result);

    }

    @Test
    void getProjectNumberOfPlannedSprints() {

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectNumberOfPlannedSprints numberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(0);

        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();


        ProjectNumberOfPlannedSprints result = project.getProjectNumberOfPlannedSprints();


        //assert

        assertEquals(numberOfPlannedSprints, result);
    }

    @Test
    void getStatus() {
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectStatus status = ProjectStatus.PLANNED;


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();


        ProjectStatus result = project.getStatus();


        //assert

        assertEquals(status, result);

    }


    @Test
    void getTimePeriodNotNull() {
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        LocalDate startDate = LocalDate.of(2022, 05, 10);
        LocalDate endDate = LocalDate.of(2025, 06, 15);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).projectTimePeriod(timePeriod).build();


        TimePeriod result = project.getTimePeriod();


        //assert

        assertEquals(timePeriod, result);

    }

    @Test
    void getProjectBusinessSector() {
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectBusinessSector businessSector = ProjectBusinessSector.createProjectBusinessSector("Tech");


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).projectBusinessSector(businessSector).build();


        ProjectBusinessSector result = project.getProjectBusinessSector();


        //assert

        assertEquals(businessSector, result);

    }


    @Test
    void setTimePeriodTrue() {
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        LocalDate startDate = LocalDate.of(2022, 05, 10);
        LocalDate endDate = LocalDate.of(2025, 06, 15);
        TimePeriod newTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();

        boolean result = project.setTimePeriod(newTimePeriod);


        //assert

        assertTrue(result);

    }


    @Test
    void setTimePeriodFalse() {
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        LocalDate startDate = LocalDate.of(2022, 05, 10);
        LocalDate endDate = LocalDate.of(2025, 06, 15);


        TimePeriod newTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).projectTimePeriod(newTimePeriod).build();


        boolean result = project.setTimePeriod(newTimePeriod);


        //assert

        assertFalse(result);

    }

    @Test
    void validateSprintDatesWithProjectDates_FailScenario_SprintIsBeforeProjectDates() {
        //Arrange
        TimePeriod newTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        TimePeriod timePeriodToTest = TimePeriod.createTimePeriod(startDate.minusDays(1), endDate);

        Project project = new Project.Builder(code, name, customer).projectTimePeriod(newTimePeriod).build();

        //Act
        boolean result = project.validateSprintDatesWithProjectDates(timePeriodToTest);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateSprintDatesWithProjectDates_FailScenario_SprintIsAfterProjectDates() {
        //Arrange
        TimePeriod newTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        TimePeriod timePeriodToTest = TimePeriod.createTimePeriod(startDate, endDate.plusDays(1));

        Project project = new Project.Builder(code, name, customer).projectTimePeriod(newTimePeriod).build();

        //Act
        boolean result = project.validateSprintDatesWithProjectDates(timePeriodToTest);

        //Assert
        assertFalse(result);
    }

    @Test
    void validateSprintDatesWithProjectDates_Success() {
        //Arrange
        TimePeriod newTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        TimePeriod timePeriodToTest = TimePeriod.createTimePeriod(startDate.plusDays(1), endDate.minusDays(1));

        Project project = new Project.Builder(code, name, customer).projectTimePeriod(newTimePeriod).build();

        //Act
        boolean result = project.validateSprintDatesWithProjectDates(timePeriodToTest);

        //Assert
        assertTrue(result);
    }

    @Test
    void validateSprintDatesWithProjectDates_ProjectDatesAreNotDefinedYet() {
        //Arrange
        TimePeriod timePeriodToTest = TimePeriod.createTimePeriod(startDate.plusDays(1), endDate.minusDays(1));

        Project project = new Project.Builder(code, name, customer).projectTimePeriod(timePeriodToTest).build();

        //Act
        boolean result = project.validateSprintDatesWithProjectDates(timePeriodToTest);

        //Assert
        assertTrue(result);
    }

    @Test
    void createNewSprint_ReturnsEmptyAfterFailingSprintDatesVerification() {
        //Arrange
        SprintFactoryInterface sprintFactoryInterfaceDouble = mock(SprintFactoryInterface.class);
        SprintID sprintID = SprintID.createSprintID("S001");
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        TimePeriod sprintTimePeriod = TimePeriod.createTimePeriod(startDate.minusDays(1), endDate);

        Project project = new Project.Builder(code, name, customer).projectTimePeriod(timePeriod).build();

        //Act
        Optional<Sprint> result = project.createNewSprint(sprintID, sprintTimePeriod, sprintStatus,
                sprintFactoryInterfaceDouble);

        //Assert
        assertEquals(Optional.empty(), result);
    }

    @Test
    void createNewSprint_ReturnsOptionalOfSprint() {
        //Arrange
        SprintFactoryInterface sprintFactoryInterfaceDouble = mock(SprintFactoryInterface.class);
        SprintID sprintID = SprintID.createSprintID("S001");
        SprintStatus sprintStatus = SprintStatus.createSprintStatus(SprintStatus.SprintStatusEnum.NOT_STARTED);

        TimePeriod timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        TimePeriod sprintTimePeriod = TimePeriod.createTimePeriod(startDate, endDate);

        Sprint sprintDouble = mock(Sprint.class);

        Project project = new Project.Builder(code, name, customer).projectTimePeriod(timePeriod).build();

        when(sprintFactoryInterfaceDouble.createSprint(sprintID, project.getProjectCode(),
                sprintTimePeriod, sprintStatus)).thenReturn(sprintDouble);

        //Act
        Optional<Sprint> result = project.createNewSprint(sprintID, sprintTimePeriod, sprintStatus,
                sprintFactoryInterfaceDouble);

        //Assert
        assertEquals(Optional.of(sprintDouble), result);
    }

    @Test
    void setProjectSprintDurationTrue() {

        //arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        SprintDuration newSprintDuration = SprintDuration.createSprintDuration(2);

        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();

        boolean result = project.setProjectSprintDuration(newSprintDuration);


        //assert

        assertTrue(result);

    }


    @Test
    void setProjectSprintDurationFalse() {

        //arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        SprintDuration newSprintDuration = SprintDuration.createSprintDuration(2);


        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).projectSprintDuration(newSprintDuration).build();


        boolean result = project.setProjectSprintDuration(newSprintDuration);


        //assert

        assertFalse(result);

    }

    @Test
    void setProjectNumberOfPlannedSprintsTrue() {

        //arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectNumberOfPlannedSprints newNumberPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(15);

        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();

        boolean result = project.setProjectNumberOfPlannedSprints(newNumberPlannedSprints);


        //assert

        assertTrue(result);

    }


    @Test
    void setProjectNumberOfPlannedSprintsFalse() {

        //arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectNumberOfPlannedSprints newNumberPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(15);

        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).
                projectNumberOfPlannedSprints(newNumberPlannedSprints).build();

        boolean result = project.setProjectNumberOfPlannedSprints(newNumberPlannedSprints);


        //assert

        assertFalse(result);

    }

    @Test
    void setProjectStatusTrue() {

        //arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectStatus newStatus = ProjectStatus.CLOSED;

        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();

        boolean result = project.setProjectStatus(newStatus);


        //assert

        assertTrue(result);

    }


    @Test
    void setProjectStatusFalse() {

        //arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectStatus newStatus = ProjectStatus.PLANNED;

        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();

        boolean result = project.setProjectStatus(newStatus);


        //assert

        assertFalse(result);

    }

    @Test
    void setProjectDescriptionTrue() {

        //arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectDescription newDescription = ProjectDescription.createProjectDescription("Change");

        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).build();

        boolean result = project.setProjectDescription(newDescription);

        //assert

        assertTrue(result);

    }


    @Test
    void setProjectDescriptionFalse() {

        //arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A123");
        ProjectCustomer projectCustomer = ProjectCustomer.createProjectCustomer("ISEP");
        ProjectName projectName = ProjectName.createProjectName("Cenas");
        ProjectDescription newDescription = ProjectDescription.createProjectDescription("Change");

        //act
        Project project = new Project.Builder(projectCode, projectName, projectCustomer).
                projectDescription(newDescription).build();

        boolean result = project.setProjectDescription(newDescription);


        //assert

        assertFalse(result);

    }

}