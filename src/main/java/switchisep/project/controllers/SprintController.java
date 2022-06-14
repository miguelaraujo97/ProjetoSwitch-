package switchisep.project.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchisep.project.ApiConfig;
import switchisep.project.dto.SprintCreationInfo;
import switchisep.project.dto.SprintUIDTO;
import switchisep.project.services.CreateSprintService;
import switchisep.project.services.ViewSprintsService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Controller class to initiate the Sprint creation process.
 */
@RestController
@Tag(name= ApiConfig.TAG_SPRINT,description=ApiConfig.TAG_SPRINT_MESSAGE)
@RequestMapping("/projects/{projectCode}/sprints")
public class SprintController {

    private final CreateSprintService createSprintService;
    private final ViewSprintsService viewSprintsService;

    public SprintController(CreateSprintService createSprintService,
                            ViewSprintsService viewSprintsService) {
        this.createSprintService = createSprintService;
        this.viewSprintsService = viewSprintsService;
    }

    @PostMapping(
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ResponseBody
    @ApiOperation(value = "US022 Create a new sprint", tags = ApiConfig.TAG_SPRINT)
    public ResponseEntity<Object> createNewSprint(@RequestBody SprintCreationInfo sprintCreationInfo,
                                                  @PathVariable(value="projectCode") String projectCode) {

        if (Objects.isNull(sprintCreationInfo.plannedStartDate)) {
            return ResponseEntity.badRequest().body("Bad data entry.");
        }

        SprintUIDTO serviceResponse =
                createSprintService.createAndSaveSprint(sprintCreationInfo, projectCode);

        Link selfLink = linkTo(methodOn(SprintController.class)
              .getSprintByID(serviceResponse.sprintID)).withSelfRel().withType("GET");

        serviceResponse.add(selfLink);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(serviceResponse);
    }

    @GetMapping(path = "/{sprintId}",
            produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Get sprint by id", tags = ApiConfig.TAG_SPRINT)
    public ResponseEntity<Object> getSprintByID(
            @PathVariable(value = "sprintId")
                    String sprintID) {

        Optional<SprintUIDTO> optSprintDto = viewSprintsService.getSprintByID(sprintID);

        if (!optSprintDto.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"No sprint found with this id.\"}");
        }

        SprintUIDTO sprintUIDTO = optSprintDto.get();

        /// HATEOAS hypermedia links
        // create link to this sprint resource
        Link link = linkTo(methodOn(SprintController.class)
                .getSprintByID(sprintID))
                .withSelfRel()
                .withType("OPTIONS");
        // add link to the project attributes
        sprintUIDTO.add(link);
        /// END HATEOAS

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sprintUIDTO);
    }

    @GetMapping(headers = "Accept=application/json",
            produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Get sprints by project code", tags = ApiConfig.TAG_SPRINT)
    public ResponseEntity<Object> getSprintsByProjectCode(@PathVariable(value = "projectCode") String projectCode){


        List<SprintUIDTO> listOfSprintsByProjectCode = viewSprintsService.getSprintsByProjectCode(projectCode);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(listOfSprintsByProjectCode);
    }




}
