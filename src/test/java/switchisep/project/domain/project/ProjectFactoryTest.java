package switchisep.project.domain.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectFactoryTest {
    ProjectCode code;
    ProjectName name;
    ProjectCustomer customer;
    ProjectDescription description;
    ProjectBudget budget;
    SprintDuration sprintDuration;
    ProjectNumberOfPlannedSprints numberOfPlannedSprints;
    ProjectStatus status;
    LocalDate startDate;
    LocalDate endDate;
    TimePeriod timePeriod;
    ProjectBusinessSector businessSector;
    Description typologyDescription;

    @BeforeEach
    void setup() {
        code = ProjectCode.createProjectCode("a123");
        name = ProjectName.createProjectName("Test");
        customer = ProjectCustomer.createProjectCustomer("ISEP");
        description = ProjectDescription.createProjectDescription("CENAS");
        budget = ProjectBudget.createBudget(10);
        sprintDuration = SprintDuration.createSprintDuration(10);
        numberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(10);
        status = ProjectStatus.PLANNED;
        startDate = LocalDate.of(2022, 05, 10);
        endDate = LocalDate.of(2025, 06, 15);
        timePeriod = TimePeriod.createTimePeriod(startDate, endDate);
        businessSector = ProjectBusinessSector.createProjectBusinessSector("School");
        typologyDescription = Description.createDescription("Fixed Costs");

    }

    @Test
    void createProject() {

        ProjectFactory projectFactory = new ProjectFactory();

       Project project =  projectFactory.createProject(code, name, customer, description, budget,sprintDuration,status,
                numberOfPlannedSprints,typologyDescription,timePeriod,businessSector);

       Project expected = new Project.Builder(code, name, customer).projectTypologyDescription(typologyDescription).projectStatus(status)
               .projectSprintDuration(sprintDuration).projectDescription(description).projectBudget(budget)
               .projectNumberOfPlannedSprints(numberOfPlannedSprints).projectTimePeriod(timePeriod).projectBusinessSector(businessSector).build();

       assertEquals(expected,project);

    }
}