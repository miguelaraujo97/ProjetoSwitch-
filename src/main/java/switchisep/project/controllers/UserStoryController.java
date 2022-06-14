package switchisep.project.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchisep.project.ApiConfig;
import switchisep.project.dto.*;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.services.AddUsToSprintBacklogService;
import switchisep.project.services.CreateUserStoryService;
import switchisep.project.services.GetUserStoryService;
import switchisep.project.services.ProductBacklogSortedByPriorityService;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/projects/{projectCode}/user-stories")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = ApiConfig.TAG_USER_STORY, description =
        ApiConfig.TAG_USER_STORY_MESSAGE)
public class UserStoryController {

    private final CreateUserStoryService createUserStoryService;

    private final UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;

    private final ProductBacklogSortedByPriorityService productBacklogSortedByPriorityService;

    private final GetUserStoryService getUserStoryService;

    private final AddUsToSprintBacklogService addUsToSprintbacklogService;

    public UserStoryController(CreateUserStoryService createUserStoryService,
                               UserStoryDomainDTOAssembler userStoryDomainDTOAssembler,
                               ProductBacklogSortedByPriorityService productBacklogSortedByPriorityService,
                               GetUserStoryService getUserStoryService,
                               AddUsToSprintBacklogService addUsToSprintbacklogService) {

        this.createUserStoryService = createUserStoryService;
        this.userStoryDomainDTOAssembler = userStoryDomainDTOAssembler;
        this.productBacklogSortedByPriorityService =
                productBacklogSortedByPriorityService;
        this.getUserStoryService = getUserStoryService;
        this.addUsToSprintbacklogService = addUsToSprintbacklogService;
    }

    /**
     * Method that gets all the User Stories associated to a Project.
     *
     * @param projectCode The project that we want the User Stories of.
     * @return A list of all the User Stories of a Project. If the List is empty it returns a message.
     */

    @GetMapping(
            headers = "Accept=application/json",
            produces = "application/json")
    @ApiOperation(value = "US018 - Get Product Backlog sorted by Priority",
            tags = ApiConfig.TAG_USER_STORY)
    public ResponseEntity<Object> getProductBacklogSortedByPriority(
            @PathVariable(required = false) String projectCode) {

        Optional<List<UserStoryDTO>> usDtoList =
                productBacklogSortedByPriorityService
                        .getProductBacklogSortedByPriority(projectCode);

        if (usDtoList.isPresent()) {
            for (final UserStoryDTO userStoryDTO : usDtoList.get()) {

                Link userStoryLink = linkTo(methodOn(UserStoryController.class)
                        .getUserStoryByID(userStoryDTO.userStoryID,
                                projectCode))
                        .withSelfRel().withType("GET");

                userStoryDTO.add(userStoryLink);
            }
        }
        ResponseEntity<Object> response = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(usDtoList);

        if (usDtoList.isEmpty()) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"Project with this code: "
                            + projectCode + " do not exists!\"}");
        }
        if (usDtoList.isPresent() && usDtoList.get().isEmpty()) {
            response = ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"There are no User Stories with " +
                            "this code: " + projectCode + " \"}");
        }
        return response;
    }

    /**
     * Method that creates a new User Story.
     *
     * @param userStoryInfo User input data DTO that contains the description of the User Story to be created.
     * @param projectCode   The Project that we want to associate the User Story to.
     * @return The created User Story when successful, otherwise returns an error message.
     */


    @PostMapping(
            headers = "Accept=application/json",
            produces = "application/json")
    @ApiOperation(value = "US009 Create a user story in product backlog",
            tags = ApiConfig.TAG_USER_STORY)
    public ResponseEntity<Object> createUserStory(
            @RequestBody UserStoryInput userStoryInfo, @PathVariable(value
            = "projectCode") String projectCode) {

        NewUserStoryInfo newUserStoryInfo =
                userStoryDomainDTOAssembler.toNewUserStoryInfo(userStoryInfo.description, projectCode);

        Optional<UserStoryDTO> optionalUserStoryVoDTO =
                createUserStoryService.createAndSaveUserStory(newUserStoryInfo);

        if (optionalUserStoryVoDTO.isPresent()) {

            UserStoryDTO result = optionalUserStoryVoDTO.get();

            Link userStoryLink = linkTo(methodOn(UserStoryController.class)
                    .getUserStoryByID(result.userStoryID, projectCode)).withSelfRel();

            result.add(userStoryLink);

            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"message" +
                "\":\"User Story Already Exists\"}");
    }

    /**
     * Method that gets a User Story by its ID.
     *
     * @param userStoryId The ID of the requested User Story.
     * @param projectCode The Project that the User Story is associated to.
     * @return The requested User Story when successful, otherwise sends a error message.
     */

    @GetMapping(path = "/{userStoryId}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<Object> getUserStoryByID(@PathVariable(value =
            "userStoryId") String userStoryId, @PathVariable(value = "projectCode") String projectCode) {

        Optional<FullUserStoryDTO> optionalFullUserStoryDTO =
                getUserStoryService.getUserStoryById(userStoryId);

        if (optionalFullUserStoryDTO.isPresent()) {

            FullUserStoryDTO fullUserStoryDTO = optionalFullUserStoryDTO.get();

            Link userStoryLink =
                    linkTo(methodOn(UserStoryController.class)
                            .getUserStoryByID(userStoryId, projectCode)).withSelfRel().withType("PATCH");

            fullUserStoryDTO.add(userStoryLink);


            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(fullUserStoryDTO);

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message" +
                "\":\"No User Story found with that Id.\"}");
    }

    @PatchMapping(
            headers = "Accept=application/json",
            produces = "application/json"
    )
    @ApiOperation(value = "US023 As Team Member, I want to add a " +
            "user story in the product backlog to the sprintBacklog.", tags =
            ApiConfig.TAG_USER_STORY)
    public ResponseEntity<Object> editUserStory(
            @RequestBody UpdateUserStoryDTO updateUserStoryDTO) {
        if (updateUserStoryDTO.action.equals("addToUS")) {
            return addUsToSprintBacklog(updateUserStoryDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("Bad entry data 1");
    }

    private ResponseEntity<Object> addUsToSprintBacklog(
            UpdateUserStoryDTO updateUserStoryDTO) {
        if (updateUserStoryDTO.userStoryID.isBlank() ||
                updateUserStoryDTO.projectCode.isBlank() ||
                updateUserStoryDTO.sprintID.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Bad entry data 2");
        }
        UsToSprBacklogDTO usToSprBacklogDTO = new UsToSprBacklogDTO();
        usToSprBacklogDTO.sprintID = updateUserStoryDTO.sprintID;
        usToSprBacklogDTO.userStoryID = updateUserStoryDTO.userStoryID;
        usToSprBacklogDTO.projectCode = updateUserStoryDTO.projectCode;

        Optional<UserStoryDTO> optionalUserStoryDTO =
                addUsToSprintbacklogService.
                        addUsToSprintBacklog(usToSprBacklogDTO);
        if (!optionalUserStoryDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Project/Sprint/US not found with that code.");
        }
        UserStoryDTO userStoryDTO = optionalUserStoryDTO.get();

        return ResponseEntity.status(HttpStatus.ACCEPTED).
                contentType(MediaType.APPLICATION_JSON).
                body(userStoryDTO);
    }
}