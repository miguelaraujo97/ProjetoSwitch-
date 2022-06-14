package switchisep.project.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CreateProjectServiceInt {
/*

    @Autowired CreateProjectService createProjectService;

   @Test
    void createAndSaveProjectSuccessIntegration() {
        //arrange

        ProjectDtoNative projectDTONative = new ProjectDtoNative();
        projectDTONative.code = "a123";
        projectDTONative.name = "Things";
        projectDTONative.customer = "ISEP";
        projectDTONative.description = "Data";
        projectDTONative.budget = 0.0;
        projectDTONative.sprintDuration = 1;
        projectDTONative.numberOfPlannedSprints = 0;
        projectDTONative.status = "planned";
        projectDTONative.startDate = LocalDate.of(2022, 02, 25);
        projectDTONative.endDate = LocalDate.of(2026, 03, 22);
        projectDTONative.businessSector = "Knowledge";
        projectDTONative.typologyID = null;

        ProjectDTODomain projectDTODomain = new ProjectDTODomain();

        projectDTODomain.code = ProjectCode.createProjectCode(projectDTONative.code);
        projectDTODomain.projectID = ProjectID.createProjectID(projectDTODomain.code);
        projectDTODomain.name = ProjectName.createProjectName(projectDTONative.name);
        projectDTODomain.customer = ProjectCustomer.createProjectCustomer(projectDTONative.customer);
        projectDTODomain.description = ProjectDescription.createDescription(projectDTONative.description);
        projectDTODomain.budget = ProjectBudget.createBudget(projectDTONative.budget);
        projectDTODomain.sprintDuration = SprintDuration.createSprintDuration(projectDTONative.sprintDuration);
        projectDTODomain.status = ProjectStatus.PLANNED;
        projectDTODomain.numberOfPlannedSprints = ProjectNumberOfPlannedSprints.createNumberOfPlannedSprints(projectDTONative.numberOfPlannedSprints);
        projectDTODomain.typologyID = null;
        projectDTODomain.timePeriod = TimePeriod.createTimePeriod(projectDTONative.startDate, projectDTONative.endDate);
        projectDTODomain.businessSector = ProjectBusinessSector.createProjectBusinessSector(projectDTONative.businessSector);


        //act
        Optional<ProjectDTODomain> result = createProjectService.createProjectAndSave(projectDTONative);

        ProjectCode codeResult = result.get().code;


        //assert
        assertEquals(projectDTODomain.code,codeResult );

    }
*/

}
