package switchisep.project.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchisep.project.ApiConfig;
import switchisep.project.dto.ProfileDTO;
import switchisep.project.services.CreateProfileService;
import switchisep.project.services.ViewProfilesService;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/profiles")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = ApiConfig.TAG_PROFILE, description = ApiConfig.TAG_PROFILE_MESSAGE)
public class ProfilesController {

    CreateProfileService createProfileService;

    ViewProfilesService viewProfilesService;

    public ProfilesController(CreateProfileService createProfileService, ViewProfilesService viewProfilesService) {
        this.createProfileService = createProfileService;
        this.viewProfilesService = viewProfilesService;
    }

    /**
     * Method that creates a new Profile.
     *
     * @param profile User input data DTO that contains the profile name and Identification number.
     * @return If created successfully returns the created Profile otherwise sends a error message.
     */

    @ApiOperation(value = "US013 - Create profile", tags =
            ApiConfig.TAG_PROFILE)
    @PostMapping(
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity<Object> createProfile(
            @RequestBody ProfileDTO profile) {


        Optional<ProfileDTO> optionalProfileDTO =
                createProfileService.createAndSaveProfile(profile);

        if (!optionalProfileDTO.isPresent()) {
             return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"Profile already exists.\"}");
        }

        ProfileDTO profileDTO = optionalProfileDTO.get();

        Link profileLink = linkTo(methodOn(ProfilesController.class)
                .getProfileById(profileDTO.profileID)).withSelfRel().withType("GET");

        profileDTO.add(profileLink);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileDTO);

    }

    /**
     * Method that gets all the available Profiles.
     *
     * @return A list with all the available profiles. If the list is empty it shows an error message.
     */

    @GetMapping(headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity<Object> getAllProfiles() {

        List<ProfileDTO> allProfilesDTOList = viewProfilesService.getAllProfiles();

        if(allProfilesDTOList.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"There are no created Profiles.\"}");
        }

        for ( ProfileDTO profileDTO : allProfilesDTOList) {

            Link profileLink = linkTo(methodOn(ProfilesController.class)
                    .getProfileById(profileDTO.profileID)).withSelfRel().withType("GET");

            profileDTO.add(profileLink);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(allProfilesDTOList);
    }

    /**
     * Method that gets a Profile by its identification number
     *
     * @param profileId The ID to get the requested Profile
     * @return The requested Profile otherwise a error message.
     */

    @GetMapping( path = "/{profileId}",
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity<Object> getProfileById(@PathVariable (value = "profileId") String profileId) {

        Optional<ProfileDTO> optionalProfileDTO = viewProfilesService.getProfileById(profileId);

        if (!optionalProfileDTO.isPresent()) {

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"No Profile found with that ID.\"}");
        }

        ProfileDTO profileDTO = optionalProfileDTO.get();

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileDTO);
    }
}
