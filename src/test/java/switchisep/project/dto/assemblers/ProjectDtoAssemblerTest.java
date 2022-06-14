package switchisep.project.dto.assemblers;

import org.junit.jupiter.api.Test;
import switchisep.project.dto.ProjectDto;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.valueobjects.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class ProjectDtoAssemblerTest {

    @Test
    void toNative() {
        //arrange
        ProjectDtoAssembler projectDtoAssembler = new ProjectDtoAssembler();
        ProjectDto projectDtoExpected = new ProjectDto();

        projectDtoExpected.code = "a123";
        projectDtoExpected.name = "Things";
        projectDtoExpected.customer = "ISEP";
        projectDtoExpected.projectDescription = "Data";
        projectDtoExpected.budget = 0.0;
        projectDtoExpected.sprintDuration = 1;
        projectDtoExpected.numberOfPlannedSprints = 0;
        projectDtoExpected.status = "planned";
        projectDtoExpected.startDate = LocalDate.of(2022, 02, 25);
        projectDtoExpected.endDate = LocalDate.of(2026, 03, 22);
        projectDtoExpected.businessSector = "Knowledge";
        projectDtoExpected.typologyDescription = "Fixed Costs";

        ProjectCode code = ProjectCode.createProjectCode(projectDtoExpected.code);

        ProjectName name = ProjectName.createProjectName(projectDtoExpected.name);
        ProjectCustomer customer = ProjectCustomer.createProjectCustomer(projectDtoExpected.customer);
        ProjectDescription description = ProjectDescription.createProjectDescription(projectDtoExpected.projectDescription);
        ProjectBudget budget = ProjectBudget.createBudget(projectDtoExpected.budget);
        SprintDuration sprintDuration = SprintDuration.createSprintDuration(projectDtoExpected.sprintDuration);
        ProjectStatus status = ProjectStatus.PLANNED;
        ProjectNumberOfPlannedSprints numberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(projectDtoExpected.numberOfPlannedSprints);
        Description typologyDescription = Description.createDescription(projectDtoExpected.typologyDescription);
        TimePeriod timePeriod = TimePeriod.createTimePeriod(projectDtoExpected.startDate, projectDtoExpected.endDate);
        ProjectBusinessSector businessSector = ProjectBusinessSector.createProjectBusinessSector(projectDtoExpected.businessSector);


        Project project = new Project.Builder(code, name, customer).projectTypologyDescription(typologyDescription).projectStatus(status)
                .projectSprintDuration(sprintDuration).projectDescription(description).projectBudget(budget)
                .projectNumberOfPlannedSprints(numberOfPlannedSprints).projectTimePeriod(timePeriod).projectBusinessSector(businessSector).build();

        //act
       ProjectDto result =  projectDtoAssembler.toNative(project);

       //assert
       assertEquals(projectDtoExpected,result);

    }
}