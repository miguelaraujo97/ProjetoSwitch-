package switchisep.project.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import switchisep.project.dto.FinalProjectActivityStatusDTOList;
import switchisep.project.dto.ProjectActivityStatusDTO;
import switchisep.project.services.ViewActivitiesStatusService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ExtendWith(MockitoExtension.class)
class ProjectActivitiesControllerTest {

    @InjectMocks
    ProjectActivitiesController projectActivitiesController;

    @Mock
    ViewActivitiesStatusService viewActivitiesStatusService;
    @Mock
    ProjectController projectController;


    @Test
    void viewStatusOfActivitiesInAProject_success() {

        //Arrange

        String projectCode = "A0001";

        ProjectActivityStatusDTO projectActivityStatusDTO_1 = new ProjectActivityStatusDTO();
        ProjectActivityStatusDTO projectActivityStatusDTO_2 = new ProjectActivityStatusDTO();
        ProjectActivityStatusDTO projectActivityStatusDTO_3 = new ProjectActivityStatusDTO();

        projectActivityStatusDTO_1.activityID = "us_id_1";
        projectActivityStatusDTO_2.activityID = "us_id_2";
        projectActivityStatusDTO_3.activityID = "us_id_3";

        projectActivityStatusDTO_1.activityDescription = "us_desc_1";
        projectActivityStatusDTO_2.activityDescription = "us_desc_2";
        projectActivityStatusDTO_3.activityDescription = "us_desc_3";

        projectActivityStatusDTO_1.activityStatus = "us_status_1";
        projectActivityStatusDTO_2.activityStatus = "us_status_2";
        projectActivityStatusDTO_3.activityStatus = "us_status_3";

        //---------------------------------------------------------------------------------------------------//


        Link selfLinkProjectActivityStatusDTO_1 = linkTo(methodOn(ProjectController.class).getProjectByCode(projectCode))
                .slash("user-stories")
                .slash(projectActivityStatusDTO_1.activityID)
                .withSelfRel().withType("GET");
        Link selfLinkProjectActivityStatusDTO_2 = linkTo(methodOn(ProjectController.class).getProjectByCode(projectCode))
                .slash("user-stories")
                .slash(projectActivityStatusDTO_2.activityID)
                .withSelfRel().withType("GET");
        Link selfLinkProjectActivityStatusDTO_3 = linkTo(methodOn(ProjectController.class).getProjectByCode(projectCode))
                .slash("user-stories")
                .slash(projectActivityStatusDTO_3.activityID)
                .withSelfRel().withType("GET");

        projectActivityStatusDTO_1.add(selfLinkProjectActivityStatusDTO_1);
        projectActivityStatusDTO_2.add(selfLinkProjectActivityStatusDTO_2);
        projectActivityStatusDTO_3.add(selfLinkProjectActivityStatusDTO_3);


        //---------------------------------------------------------------------------------------------------//

        List<ProjectActivityStatusDTO> projectActivitiesStatusDTOList = new ArrayList<>();

        projectActivitiesStatusDTOList.add(projectActivityStatusDTO_1);
        projectActivitiesStatusDTOList.add(projectActivityStatusDTO_2);
        projectActivitiesStatusDTOList.add(projectActivityStatusDTO_3);

        //---------------------------------------------------------------------------------------------------//

        when(viewActivitiesStatusService.getAllProjectActivities(projectCode)).thenReturn(Optional.of(projectActivitiesStatusDTOList));

        //---------------------------------------------------------------------------------------------------//


        List<ProjectActivityStatusDTO> activitiesStatusList = new ArrayList<>();


        FinalProjectActivityStatusDTOList finalProjectActivityStatusDTOList = new FinalProjectActivityStatusDTOList();

        finalProjectActivityStatusDTOList.activitiesStatusList = activitiesStatusList;


        //---------------------------------------------------------------------------------------------------//

        Link sel = linkTo(methodOn(ProjectController.class).getProjectByCode(projectCode)).withSelfRel().withType("GET");
        finalProjectActivityStatusDTOList.add(sel);

        //---------------------------------------------------------------------------------------------------//



        ResponseEntity expected = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(finalProjectActivityStatusDTOList);

        //Act
        ResponseEntity result = projectActivitiesController.viewStatusOfActivitiesInAProject(projectCode);

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void viewStatusOfActivitiesInAProject_fail_projectCodeNotFound() {

        //Arrange

        String projectCode = "A0002";

        ProjectActivityStatusDTO projectActivityStatusDTO_1 = new ProjectActivityStatusDTO();
        ProjectActivityStatusDTO projectActivityStatusDTO_2 = new ProjectActivityStatusDTO();
        ProjectActivityStatusDTO projectActivityStatusDTO_3 = new ProjectActivityStatusDTO();

        projectActivityStatusDTO_1.activityID = "us_id_1";
        projectActivityStatusDTO_2.activityID = "us_id_2";
        projectActivityStatusDTO_3.activityID = "us_id_3";

        projectActivityStatusDTO_1.activityDescription = "us_desc_1";
        projectActivityStatusDTO_2.activityDescription = "us_desc_2";
        projectActivityStatusDTO_3.activityDescription = "us_desc_3";

        projectActivityStatusDTO_1.activityStatus = "us_status_1";
        projectActivityStatusDTO_2.activityStatus = "us_status_2";
        projectActivityStatusDTO_3.activityStatus = "us_status_3";



        //---------------------------------------------------------------------------------------------------//

        List<ProjectActivityStatusDTO> projectActivitiesStatusDTOList = new ArrayList<>();

        projectActivitiesStatusDTOList.add(projectActivityStatusDTO_1);
        projectActivitiesStatusDTOList.add(projectActivityStatusDTO_2);
        projectActivitiesStatusDTOList.add(projectActivityStatusDTO_3);

        //---------------------------------------------------------------------------------------------------//

        when(viewActivitiesStatusService.getAllProjectActivities(projectCode)).thenReturn(Optional.empty());

        //---------------------------------------------------------------------------------------------------//


        List<ProjectActivityStatusDTO> activitiesStatusList = new ArrayList<>();


        FinalProjectActivityStatusDTOList finalProjectActivityStatusDTOList = new FinalProjectActivityStatusDTOList();

        finalProjectActivityStatusDTOList.activitiesStatusList = activitiesStatusList;



        //---------------------------------------------------------------------------------------------------//



        ResponseEntity expected = ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                .body("Project Code NOT Found!");

        //Act
        ResponseEntity result = projectActivitiesController.viewStatusOfActivitiesInAProject(projectCode);

        //Assert
        assertEquals(expected, result);

    }

    @Test
    void viewStatusOfActivitiesInAProject_fail_projectCodeWasNotAValidInput() {

        //Arrange

        String projectCode = "";


        ResponseEntity expected = ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON)
                .body("Project Code must be inserted!");

        //Act
        ResponseEntity result = projectActivitiesController.viewStatusOfActivitiesInAProject(projectCode);

        //Assert
        assertEquals(expected, result);

    }
}