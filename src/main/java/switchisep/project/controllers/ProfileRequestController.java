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
import switchisep.project.dto.ProfileRequestDTO;
import switchisep.project.dto.UIProfileRequestDTO;
import switchisep.project.services.ProfileRequestService;
import switchisep.project.services.ViewProfileRequestService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/requests")
@Tag(name= ApiConfig.TAG_REQUEST,description=ApiConfig.TAG_REQUEST_MESSAGE)
public class ProfileRequestController {

    @Autowired
    ProfileRequestService profileRequestService;

    @Autowired
    ViewProfileRequestService viewProfileRequestService;

    /**
     * Method that creates a Profile Request associated to a User and a Profile.
     *
     * @param uiProfileRequestDTO User input DTO from the UI to create a Profile Request.
     *                            This DTO contains a profileName and a userId.
     *
     * @return If created successfully returns the created Profile Request otherwise sends a error message.
     */

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "US003 - As Visitor, I want to send a request to the administrator to assign him/her a given profile.", tags = ApiConfig.TAG_REQUEST)
    public ResponseEntity<Object> profileRequest(@RequestBody UIProfileRequestDTO
                                         uiProfileRequestDTO) {

        if (Objects.nonNull(uiProfileRequestDTO)  &&
                !uiProfileRequestDTO.profileName.isEmpty() &&
                !uiProfileRequestDTO.profileName.isBlank()) {

        } else return ResponseEntity.badRequest().
                body("Bad entry data");

        Optional<ProfileRequestDTO> optRequestProfile = profileRequestService.
                createAndSaveProfileRequest(uiProfileRequestDTO);

        if (!optRequestProfile.isPresent()) {
            return ResponseEntity.badRequest().
                    body("User Id/Profile doesnt exist");
        }

        ProfileRequestDTO profileRequestDTO = optRequestProfile.get();

        Link selfLink = linkTo(methodOn(ProfileRequestController.class)
                .getProfileRequestById(profileRequestDTO.profileRequestId)).withSelfRel().withType("GET");

        profileRequestDTO.add(selfLink);

        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileRequestDTO);
    }

    /**
     * Method that returns a Profile Request found by its ID
     *
     * @param profileRequestId The ID to get the requested Profile Request
     * @return The requested Profile Request otherwise a error message.
     */

    @GetMapping(path = "/{profileRequestId}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity<Object> getProfileRequestById(
            @PathVariable(value = "profileRequestId") int profileRequestId) {

        Optional<ProfileRequestDTO> optionalProfileRequestDTO =
                viewProfileRequestService.getProfileRequestById(profileRequestId);

        if (!optionalProfileRequestDTO.isPresent()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"Profile Request not found\"}");
        }

        ProfileRequestDTO profileRequestDTO = optionalProfileRequestDTO.get();

        Link userLink = linkTo(methodOn(UserController.class)
                .searchUserByID(profileRequestDTO.userID)).withRel("users").withType("GET");

        profileRequestDTO.add(userLink);

        Link profileLink = linkTo(methodOn(ProfilesController.class)
                .getProfileById(profileRequestDTO.profileID)).withRel("profiles").withType("GET");

        profileRequestDTO.add(profileLink);


        return  ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileRequestDTO);
    }

    /**
     * Methods that gets all the available Profile Requests.
     *
     * @return A List with all the Profile Requests available. If the list is empty returns a message.
     */

    @GetMapping(
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity<Object> getAllProfileRequests() {

        List<ProfileRequestDTO> profileRequestDTOList =
                viewProfileRequestService.getAllProfileRequests();

        if (profileRequestDTOList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"There are currently no Profile Requests\"}");
        }

        for (ProfileRequestDTO profileRequestDTO : profileRequestDTOList) {

            Link userLink = linkTo(methodOn(ProfileRequestController.class)
                    .getProfileRequestById(profileRequestDTO.profileRequestId)).withSelfRel().withType("GET");

            profileRequestDTO.add(userLink);

        }

        return  ResponseEntity
                    .status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(profileRequestDTOList);
    }

}
