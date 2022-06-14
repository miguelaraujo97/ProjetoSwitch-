package switchisep.project.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchisep.project.ApiConfig;
import switchisep.project.dto.ResourceDTO;
import switchisep.project.dto.ResourceInputDTO;
import switchisep.project.dto.ResourceOutputDTO;
import switchisep.project.dto.assemblers.ResourceDTOAssembler;
import switchisep.project.services.CreateResourceService;
import switchisep.project.services.EditRoleService;
import switchisep.project.services.ViewResourcesService;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/projects/{projectCode}/resources")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = ApiConfig.TAG_RESOURCE, description =
        ApiConfig.TAG_RESOURCE_MESSAGE)
public class ResourceController {

    private final CreateResourceService createResourceService;

    private final EditRoleService editRoleService;

    private final ViewResourcesService viewResourcesService;

    private final ResourceDTOAssembler resourceDTOAssembler;

    public ResourceController(CreateResourceService createResourceService,
                              EditRoleService editRoleService,
                              ViewResourcesService viewResourcesService,
                              ResourceDTOAssembler resourceDTOAssembler) {

        this.createResourceService = createResourceService;
        this.editRoleService = editRoleService;
        this.viewResourcesService = viewResourcesService;
        this.resourceDTOAssembler = resourceDTOAssembler;
    }

    /**
     * Method that creates a Resource associated to a Project.
     *
     * @param resourceInputDTO User input data DTO that contains a email, start date, end date,
     *                         percentage allocation, costs per hour and role.
     *
     * @param projectCode The Project that we want to associate to.
     * @return The created Resource when successful otherwise a error message.
     */

    @PostMapping(headers = "Accept=application/json", produces = "application" +
            "/json")
    @ResponseBody
    @ApiOperation(value = "US007 - As Director, I want to associate a human " +
            "resource (user) to a project.",
            tags = ApiConfig.TAG_RESOURCE)
    public ResponseEntity<Object> addResourceToProject(@RequestBody ResourceInputDTO resourceInputDTO,
                                                       @PathVariable String projectCode) {

        ResourceDTO resourceDTO =
                resourceDTOAssembler.toDomainDTO(resourceInputDTO, projectCode);

        Optional<ResourceOutputDTO> resourceDTOOptional =
                createResourceService.createAndAdd(resourceDTO);

        if (!resourceDTOOptional.isPresent()) {

            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Resource cannot be added");

        }

        ResourceOutputDTO outputResourceDTO = resourceDTOOptional.get();

        Link selfLink = linkTo(methodOn(ResourceController.class)
                .getResourceByID(outputResourceDTO.resourceId)).withSelfRel().withType("GET");

        outputResourceDTO.add(selfLink);

        return ResponseEntity.status(HttpStatus.CREATED).body(outputResourceDTO);

    }

    /**
     * Method that gets all Resources that are associated to a Project.
     *
     * @param projectCode The Project we want the resource list of.
     * @return A List with all the Resources of a Project. If the list is empty returns a error message.
     */

    @GetMapping(headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity<Object> getResourcesOfProject(
            @PathVariable String projectCode) {

        List<ResourceOutputDTO> resourceOutputDTOS =
                viewResourcesService.getAllResourcesByProjectCode(projectCode);

        if (resourceOutputDTOS.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"No resources are allocated to this " +
                            "project.\"}");
        }

        for (ResourceOutputDTO resourceOutputDTO : resourceOutputDTOS) {

            Link selfGetLink = linkTo(methodOn(ResourceController.class)
                    .getResourceByID(resourceOutputDTO.resourceId))
                    .withSelfRel().withType("GET");

            resourceOutputDTO.add(selfGetLink);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resourceOutputDTOS);
    }

    /**
     * Method that gets a Resource by its ID.
     *
     * @param resourceId The ID of the requested Resource.
     * @return The requested Resource when successful otherwise it returns a error message.
     */

    @GetMapping(path = "/{resourceId}",
            produces = "application/json")
    public ResponseEntity<Object> getResourceByID(@PathVariable(value =
            "resourceId") String resourceId) {

        Optional<ResourceDTO> resourceDTOOptional =
                viewResourcesService.getResourceById(resourceId);

        if (!resourceDTOOptional.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"No Resource found with that Id.\"}");
        }

        ResourceDTO resourceDTO = resourceDTOOptional.get();

        Link usersLink =
                linkTo(methodOn(UserController.class).searchUserByEmail(resourceDTO.email))
                .withRel("users").withType("GET");

        resourceDTO.add(usersLink);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(resourceDTO);
    }

    @PatchMapping("")
    public ResponseEntity<Object> changeResourceSpecialRole(
            @RequestBody ResourceDTO resourceDTO, @PathVariable String projectCode) {

        editRoleService.setSpecialRole(resourceDTO.email,
                resourceDTO.startDate, resourceDTO.role,
                projectCode);

        return ResponseEntity.status(HttpStatus.OK).
                contentType(MediaType.APPLICATION_JSON).
                body(resourceDTO);
    }

}
