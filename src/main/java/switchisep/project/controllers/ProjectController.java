package switchisep.project.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchisep.project.ApiConfig;
import switchisep.project.dto.ProjectDto;
import switchisep.project.dto.UpdateProjectDto;
import switchisep.project.services.CreateProjectService;
import switchisep.project.services.EditProjectService;
import switchisep.project.services.ViewProjectService;

import java.util.*;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


/**
 * Controller class for Project.
 */
@RestController
@RequestMapping("/projects")
@CrossOrigin(origins = "*")
@Tag(name = ApiConfig.TAG_PROJECT, description = ApiConfig.TAG_PROJECT_MESSAGE)
public class ProjectController {

    private final CreateProjectService createProjectService;
    private final EditProjectService editProjectService;
    private final ViewProjectService viewProjectService;

    @Autowired
    public ProjectController(CreateProjectService createProjectService, EditProjectService editProjectService, ViewProjectService viewProjectService) {

        this.createProjectService = createProjectService;
        this.editProjectService = editProjectService;
        this.viewProjectService = viewProjectService;
    }

    /**
     * US005 - As Director, I want to register/create a new project.
     *
     * @param projectDto ProjectDto
     * @return ResponseEntity with project data
     * @throws JsonProcessingException
     * @authors Joao Reis and Ricardo Pereira
     */
    @PostMapping(
            headers = "Accept=application/json",
            produces = "application/json")
    @ApiOperation(value = "US005 Create project", tags = "Project")
    public ResponseEntity<Object> createProject(@RequestBody ProjectDto projectDto) {

        Optional<ProjectDto> optionalProjectDTO;

        if (Objects.nonNull(projectDto) &&
                !projectDto.code.isEmpty() &&
                !projectDto.name.isEmpty() &&
                !projectDto.customer.isEmpty()) {

            optionalProjectDTO = createProjectService.createProjectAndSave(projectDto);


        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Bad entry data");
        }

        if (!optionalProjectDTO.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Project code already exists");
        }

        ProjectDto outProjectDto = optionalProjectDTO.get();

        /// HATEOAS hypermedia links
        // create hypermedia links
        Link link = linkTo(methodOn(ProjectController.class)
                .getProjectByCode(projectDto.code))
                .withSelfRel()
                .withType("GET, PATCH");

        outProjectDto.add(link);


        /// END HATEOAS

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(outProjectDto);
    }


    /**
     * US008 - As Project Manager, I want to edit some project information.
     *
     * @param setNewData ProjectDto
     * @return ResponseEntity with project data
     * @authors Joao Reis and Ricardo Pereira
     */
    @PatchMapping(path = "/{projectCode}",
            headers = "Accept=application/json",
            produces = "application/json")
    @ApiOperation(value = "US008 Update project by id", tags = ApiConfig.TAG_PROJECT)
    public ResponseEntity<Object> editProject(
            @PathVariable(value = "projectCode", required = true) String projectCode,
            @RequestBody UpdateProjectDto setNewData) {

        Optional<ProjectDto> optionalProjectDTO;

        if (Objects.nonNull(setNewData) && !projectCode.isBlank()) {

            optionalProjectDTO = editProjectService.setNewData(setNewData);

        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad entry data");

        }
        if (!optionalProjectDTO.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No project found with that code.");
        }

        ProjectDto outProjectDto = optionalProjectDTO.get();

        /// HATEOAS hypermedia links
        // create hypermedia links
        Link link = linkTo(methodOn(ProjectController.class)
                .getProjectByCode(outProjectDto.code))
                .withSelfRel()
                .withType("GET, PATCH");

        outProjectDto.add(link);


        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(outProjectDto);

    }

    /**
     * US015 - As Director, I want to get a list of all projects.
     *
     * @return a ResponseEntity with a list of all projects
     * @authors Joao Reis and Ricardo Pereira
     */
    @GetMapping(
            produces = "application/json"
    )
    @ResponseBody
    @ApiOperation(value = "US015 Get all projects", tags = ApiConfig.TAG_PROJECT)
    public ResponseEntity<Object> getAllProjects(
            @PageableDefault(page = 0, size = 2)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "code", direction = Sort.Direction.ASC),
                    //@SortDefault(sort = "customer", direction = Sort.Direction.DESC),
            }) Pageable paging
    ) {

        Page<ProjectDto> pageProjects = viewProjectService.getAllProjects(paging);

        List<ProjectDto> projects = pageProjects.getContent();

        // must be removed because hateoas links needed
        if (projects.isEmpty()) {
            //nResponse.put("status_message", "No data");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(pageProjects);
        }

        /// HATEOAS hypermedia links
        // create hypermedia links
        for (final ProjectDto projectDto : projects) {
            // add link to the dto attributes
            projectDto.add(
                    linkTo(methodOn(ProjectController.class)
                            .getProjectByCode(projectDto.code))
                            .withSelfRel()
                            .withType("GET")
            );
        }
        /// END HATEOAS

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(pageProjects);
    }


    /**
     * As Director, I want to get a project.
     *
     * @param projectCode String
     * @return a ResponseEntity with the project asked.
     * @authors Joao Reis and Ricardo Pereira.
     */

    @GetMapping(path = "/{projectCode}",
            produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "Get project by id", tags = ApiConfig.TAG_PROJECT)
    public ResponseEntity<Object> getProjectByCode(
            @PathVariable(value = "projectCode", required = true)
                    String projectCode
    ) {
        Optional<ProjectDto> optProjectDto;

        optProjectDto = viewProjectService.getProjectByCode(projectCode);


        if (!optProjectDto.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"No project found with that code.\"}");
        }

        ProjectDto projectDto = optProjectDto.get();

        /// HATEOAS hypermedia links
        // create link to the project page
        //Link selfLink = linkTo(WebMvcLinkBuilder.methodOn(ProjectController.class).getAllProjects()).withRel("projects").withType("GET");
        Link link = linkTo(methodOn(ProjectController.class).getProjectByCode(projectCode)).withSelfRel().withType("OPTIONS");
        // add link to the project attributes
        projectDto.add(link);
        /// END HATEOAS



        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(projectDto);
    }
}



