package switchisep.project.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import switchisep.project.ApiConfig;
import switchisep.project.dto.*;
import switchisep.project.services.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
@Tag(name = ApiConfig.TAG_USER, description = ApiConfig.TAG_USER_MESSAGE)
public class UserController {


    private final ProjectsUserIsAllocatedService projectsUserIsAllocatedService;
    private final RegisterUserAppService registerUserAppService;
    private final UpdateUserProfileService updateUserProfileService;
    private final EditUserService editUserService;
    private final ActivateNewUserService activateNewUserService;

    private final ViewUsersService viewUsersService;

    private final ChangeUserPasswordService changeUserPasswordService;
    private final String badEntry = "Bad entry data";
    private final String userNotFound = "User not found";

    public UserController(ProjectsUserIsAllocatedService projectsUserIsAllocatedService, RegisterUserAppService registerUserAppService, UpdateUserProfileService updateUserProfileService, EditUserService editUserService, ActivateNewUserService activateNewUserService, ViewUsersService viewUsersService, ChangeUserPasswordService changeUserPasswordService) {

        this.projectsUserIsAllocatedService = projectsUserIsAllocatedService;
        this.registerUserAppService = registerUserAppService;
        this.updateUserProfileService = updateUserProfileService;
        this.editUserService = editUserService;
        this.activateNewUserService = activateNewUserService;
        this.viewUsersService = viewUsersService;
        this.changeUserPasswordService = changeUserPasswordService;
    }

    @PostMapping(headers = "Accept=application/json",
            produces = "application/json")
    @ResponseBody
    @ApiOperation(value = "US001 Create user", tags = "User")
    public ResponseEntity<Object> createNewUser(@RequestBody
                                                        UserRegistrationRequest userRegistrationRequest) {

        Optional<UserUIInfoDTO> opUserDTO;
        if (Objects.nonNull(userRegistrationRequest) &&
                !userRegistrationRequest.userName.isEmpty()
                && !userRegistrationRequest.email.isEmpty() &&
                !userRegistrationRequest.function.isEmpty() &&
                !userRegistrationRequest.password.isEmpty() &&
                !userRegistrationRequest.photo.isEmpty() &&
                !userRegistrationRequest.userName.isBlank()
                && !userRegistrationRequest.email.isBlank() &&
                !userRegistrationRequest.function.isBlank() &&
                !userRegistrationRequest.password.isBlank() &&
                !userRegistrationRequest.photo.isBlank()) {
            opUserDTO = registerUserAppService.createAndSaveUser(
                    userRegistrationRequest);
        } else return ResponseEntity.badRequest().body(badEntry);

        if (!opUserDTO.isPresent()) {
            return ResponseEntity.badRequest().body(
                    "Email already exists");
        }

        UserUIInfoDTO userUIInfoDTO = opUserDTO.get();

        Link userLink = linkTo(methodOn(UserController.class)
                .searchUserByID(userUIInfoDTO.userId)).withSelfRel().withType("GET");

        userUIInfoDTO.add(userLink);

        return ResponseEntity.status(HttpStatus.CREATED).
                body(userUIInfoDTO);
    }


    /**
     * US017 - As Authenticated User, I want to get a list of all projects
     * I'm currently allocated to.
     *
     * @param email String
     * @return ResponseEntity with list of projects data
     * @authors Joao Reis and Ricardo Pereira
     */
    @GetMapping(path = "/{userId}/projects",
            produces = "application/json")
    @ApiOperation(value = "US017 - List of all projects I'm currently " +
            "allocated to.", tags = "User")
    public ResponseEntity<Object> getProjectsUserIsCurrentlyAllocated(
            @PathVariable(value = "userId") String email) {

        List<ProjectDto> projectDtoList =
                projectsUserIsAllocatedService
                        .getProjectsUserIsCurrentlyAllocated(email);


        if (projectDtoList.isEmpty()) {

            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(projectDtoList);


        } else
            for (ProjectDto projectDto : projectDtoList) {

                // create link to the project page
                Link selfLinkProjectDto =
                        linkTo(WebMvcLinkBuilder.methodOn(ProjectController.class)
                                .getProjectByCode(projectDto.code)).withRel(
                                "projects"
                        ).withType("GET");
                // add link (if not exist) to the project attributes
                projectDto.add(selfLinkProjectDto);
                /// END HATEOAS
            }


        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON).body(projectDtoList);
    }

    @GetMapping(
            headers = "Accept=application/json",
            produces = "application/json")
    public ResponseEntity<Object> getAllUsers() {

        List<UserUiDTO> userUiDTOSList = viewUsersService.getAllUsers();

        if (userUiDTOSList.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message\":\"There are currently no Users " +
                            "created\"}");
        }

        for (UserUiDTO userUiDTO : userUiDTOSList) {

            Link selfGetLink = linkTo(methodOn(UserController.class)
                    .searchUserByID(userUiDTO.userId)).withSelfRel()
                    .withType("GET");

            userUiDTO.add(selfGetLink);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userUiDTOSList);
    }

    @GetMapping("/by-email/{email}")
    @ApiOperation(value = "US004 - Search for users by e-mail", tags = "User")
    public ResponseEntity<Object> searchUserByEmail(@PathVariable(value =
            "email") String email) {

        Optional<UserUiDTO> userFound = viewUsersService.getUserByEmail(email);

        if (!userFound.isPresent()) {

            return ResponseEntity.badRequest().body(userNotFound);

        }

        UserUiDTO userUiDTO = userFound.get();

        Link link =
                linkTo(methodOn(UserController.class)
                        .searchUserByID(userUiDTO.userId)).withSelfRel();

        userUiDTO.add(link);

        return ResponseEntity.ok(userFound);
    }

    @GetMapping("/by-profile/{profile}")
    @ApiOperation(value = "US004 - Search for users by profile", tags = "User")
    public ResponseEntity<Object> searchUserByProfile(@PathVariable(value =
            "profile") String profile) {

        List<UserUiDTO> usersFound = viewUsersService.getUserByProfile(profile);

        if (usersFound.isEmpty()) {

            return ResponseEntity.badRequest().body(userNotFound);

        }

        for (UserUiDTO userUiDTO : usersFound) {

            Link userLink = linkTo(methodOn(UserController.class)
                    .searchUserByID(userUiDTO.userId)).withSelfRel().withType("GET");

            userUiDTO.add(userLink);
        }

        return ResponseEntity.ok(usersFound);
    }

    @GetMapping("/{userID}")
    @ApiOperation(value = "Search for users by profile", tags = "User")
    public ResponseEntity<Object> searchUserByID(@PathVariable(value =
            "userID") int userID) {

        Optional<UserUIInfoDTO> userFound =
                viewUsersService.getUserByID(userID);

        if (!userFound.isPresent()) {

            return ResponseEntity
                    .badRequest()
                    .body(userNotFound);

        } else {
            UserUIInfoDTO userDTO = userFound.get();

            Link profileLink =
                    linkTo(WebMvcLinkBuilder.methodOn(ProfilesController.class)
                            .getProfileById(userDTO.profileId)).withRel(
                            "profiles").withType("GET");

            userDTO.add(profileLink);

            Link selfPatchLink = linkTo(methodOn(UserController.class)
                    .searchUserByID(userID)).withSelfRel().withType("PATCH");

            userDTO.add(selfPatchLink);

            return ResponseEntity.ok(userFound);
        }

    }

    @PatchMapping(
            headers = "Accept=application/json",
            produces = "application/json")

    @ApiOperation(value = "Edit password/function/photo or activate user",
            tags = ApiConfig.TAG_USER)
    public ResponseEntity<Object> patchUser(
            @RequestBody EditUserDTO editUserDTO) {

        if (editUserDTO.userID > 0
                && editUserDTO.action.equals("PasswordUpdate")) {
            return updatePassword(editUserDTO);
        } else if (editUserDTO.userID > 0
                && editUserDTO.action.equals("ProfileUpdate")) {
            return updateUserProfile(editUserDTO);
        } else if (editUserDTO.userID > 0
                && editUserDTO.action.equals("ActivateUser")) {
            return activateNewUser(editUserDTO);
        } else if (editUserDTO.userID > 0 &&
                (editUserDTO.action.equals("EditPhoto")
                        || editUserDTO.action.equals("EditFunction")
                )) {
            return editUserInfo(editUserDTO);
        } else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Forbidden operation, validate parameters");
    }

    public ResponseEntity<Object> updatePassword(EditUserDTO editUserDTO) {

        if (Objects.isNull(editUserDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badEntry);
        }

        Optional<UserUIInfoDTO> optionalChangePasswordToUIDTO =
                changeUserPasswordService.setNewPassword(editUserDTO);

        if (!optionalChangePasswordToUIDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User " +
                    "found with that ID");
        }

        UserUIInfoDTO editedPasswordDTO = optionalChangePasswordToUIDTO.get();

        Link link =
                linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                        .searchUserByID(editUserDTO.userID))
                        .withSelfRel().withType("PATCH");

        editedPasswordDTO.add(link);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON).body(editedPasswordDTO);
    }

    public ResponseEntity<Object> updateUserProfile(
            @RequestBody EditUserDTO editUserDTO) {
        Optional<UserUiDTO> opUserDTO;
        if (Objects.nonNull(editUserDTO) &&
                !editUserDTO.profileID.isEmpty()
                && !editUserDTO.profileID.isBlank()) {
            opUserDTO = updateUserProfileService.profileUpdate(
                    editUserDTO
            );
        } else return ResponseEntity.badRequest().body(badEntry);

        if (!opUserDTO.isPresent()) {
            return ResponseEntity.badRequest().body(
                    "User or Profile doesn't exist with these IDs");
        }

        return ResponseEntity.status(HttpStatus.CREATED).
                body(opUserDTO);
    }

    public ResponseEntity<Object> editUserInfo(
            @RequestBody EditUserDTO editUserDTO) {

        Optional<UserUIInfoDTO> optionalEditUserDTO;

        optionalEditUserDTO = editUserService.setNewUserData(editUserDTO);

        if (editUserDTO.userEmail.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad " +
                    "entry data or email is missing");
        }
        if (optionalEditUserDTO.isPresent()) {

            UserUIInfoDTO outEditedUserInfoDTO = optionalEditUserDTO.get();

            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(outEditedUserInfoDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User " +
                "found with that email address");
    }

    public ResponseEntity<Object> activateNewUser(EditUserDTO editUserDTO) {

        if (activateNewUserService.activateNewUser(editUserDTO.userID)) {
            return new ResponseEntity<>("User was successfully activated.",
                    HttpStatus.OK);
        } else
            return new ResponseEntity<>("User was not found.",
                    HttpStatus.NOT_FOUND);
    }


}
