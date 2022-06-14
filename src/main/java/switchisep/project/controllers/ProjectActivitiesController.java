package switchisep.project.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchisep.project.ApiConfig;
import switchisep.project.dto.FinalProjectActivityStatusDTOList;
import switchisep.project.dto.ProjectActivityStatusDTO;
import switchisep.project.services.ViewActivitiesStatusService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/projects/{projectCode}")
@CrossOrigin(origins = "*")
@Tag(name = ApiConfig.TAG_ACTIVITIES, description = ApiConfig.TAG_ACTIVITIES_MESSAGE)
public class ProjectActivitiesController {

    @Autowired
    ViewActivitiesStatusService viewActivitiesStatusService;

    @GetMapping(path = "/activities", produces = "application/json")
    @ApiOperation(value = "US016 - Get all Activities Status of a Project", tags = ApiConfig.TAG_ACTIVITIES)
    public ResponseEntity<Object> viewStatusOfActivitiesInAProject(@PathVariable(value = "projectCode") String projectCode) {


        if (Objects.isNull(projectCode) || projectCode.isBlank()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON)
                    .body("Project Code must be inserted!");
        }


        Optional<List<ProjectActivityStatusDTO>> projectActivityStatusDTOList = viewActivitiesStatusService
                .getAllProjectActivities(projectCode);


        if (!projectActivityStatusDTOList.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON)
                    .body("Project Code NOT Found!");
        }


        for (ProjectActivityStatusDTO projectActivityStatusDTO : projectActivityStatusDTOList.get()) {

            Link selfLinkProjectActivityStatusDTO = linkTo(methodOn(ProjectController.class).getProjectByCode(projectCode))
                    .slash("user-stories")
                    .slash(projectActivityStatusDTO.activityID)
                    .withSelfRel().withType("GET");

            projectActivityStatusDTO.add(selfLinkProjectActivityStatusDTO);
        }


        Link projectLink = linkTo(methodOn(ProjectController.class).getProjectByCode(projectCode)).withSelfRel().withType("GET");


        FinalProjectActivityStatusDTOList finalProjectActivityStatusDTOList = new FinalProjectActivityStatusDTOList();

        finalProjectActivityStatusDTOList.add(projectLink);

        finalProjectActivityStatusDTOList.activitiesStatusList = projectActivityStatusDTOList.get();

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(finalProjectActivityStatusDTOList);
    }


}