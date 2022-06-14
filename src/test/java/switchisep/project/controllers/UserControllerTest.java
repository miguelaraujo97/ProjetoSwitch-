package switchisep.project.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import switchisep.project.dto.*;
import switchisep.project.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;
    @Mock
    ProjectsUserIsAllocatedService projectsUserIsAllocatedService;
    @Mock
    RegisterUserAppService registerUserAppService;
    @Mock
    UpdateUserProfileService updateUserProfileService;
    @Mock
    EditUserService editUserService;
    @Mock
    ActivateNewUserService activateNewUserService;
    @Mock
    ViewUsersService viewUsersService;
    @Mock
    ChangeUserPasswordService changeUserPasswordService;

    //Create User endpoint tests
    @Test
    void createUserSuccess() {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "11";
        userRegistrationRequest.email = "111111@111.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "111";

        UserUIInfoDTO userUIInfoDTO = mock(UserUIInfoDTO.class);
        when(registerUserAppService.
                createAndSaveUser(userRegistrationRequest)).thenReturn(
                Optional.of(userUIInfoDTO));

        //act
        ResponseEntity<Object> expected = ResponseEntity.
                status(HttpStatus.CREATED).body(userUIInfoDTO);
        ResponseEntity<Object> result = userController.
                createNewUser(userRegistrationRequest);
        //assert
        assertEquals(expected, result);
    }

    @Test
    void createUserSuccess_() {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "11";
        userRegistrationRequest.email = "111111@111.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "111";

        UserUIInfoDTO userUIInfoDTO = mock(UserUIInfoDTO.class);
        when(registerUserAppService.
                createAndSaveUser(any())).thenReturn(
                Optional.of(userUIInfoDTO));

        //act
        ResponseEntity<Object> expected = ResponseEntity.
                status(HttpStatus.CREATED).body(userUIInfoDTO);
        ResponseEntity<Object> result = userController.
                createNewUser(userRegistrationRequest);
        //assert
        assertEquals(expected, result);
    }

    @Test
    void createUserAlreadyExists() {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "11";
        userRegistrationRequest.email = "111111@111.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "111";

        UserInternalDTO userInternalDTO = mock(UserInternalDTO.class);
        when(registerUserAppService.
                createAndSaveUser(any())).thenReturn(
                Optional.empty());

        //act
        ResponseEntity<Object> expected = ResponseEntity.
                badRequest().body("Email already exists");
        ResponseEntity<Object> result = userController.
                createNewUser(userRegistrationRequest);
        //assert
        assertEquals(expected, result);
    }

    @Test
    void createUserBadRequestNull() {
        //arrange
        //act
        ResponseEntity<Object> expected = ResponseEntity.
                badRequest().body("Bad entry data");
        ResponseEntity<Object> result = userController.
                createNewUser(null);        //assert
        //assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n", "\r"})
    void createUserBadRequest_FailInvalidUserInput(String values) {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = values;
        userRegistrationRequest.email = "111111@111.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "111";

        //act
        ResponseEntity<Object> expected = ResponseEntity.
                badRequest().body("Bad entry data");
        ResponseEntity<Object> result = userController.
                createNewUser(userRegistrationRequest);        //assert
        //assert
        assertEquals(expected, result);
    }


    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n", "\r"})
    void createUserBadRequest_FailInvalidInputEmail(String values) {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "11";
        userRegistrationRequest.email = values;
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "111";

        //act
        ResponseEntity<Object> expected = ResponseEntity.
                badRequest().body("Bad entry data");
        ResponseEntity<Object> result = userController.
                createNewUser(userRegistrationRequest);
        //assert
        //assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n", "\r"})
    void createUserBadRequest_FailInvalidInputFunction(String values) {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "11";
        userRegistrationRequest.email = "111111@111.com";
        userRegistrationRequest.function = values;
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "111";

        //act
        ResponseEntity<Object> expected = ResponseEntity.
                badRequest().body("Bad entry data");
        ResponseEntity<Object> result = userController.
                createNewUser(userRegistrationRequest);
        //assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n", "\r"})
    void createUserBadRequest_FailInvalidInputPassword(String values) {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "11";
        userRegistrationRequest.email = "111111@111.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = values;
        userRegistrationRequest.photo = "111";

        //act
        ResponseEntity<Object> expected = ResponseEntity.
                badRequest().body("Bad entry data");
        ResponseEntity<Object> result = userController.
                createNewUser(userRegistrationRequest);
        //assert
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n", "\r"})
    void createUserBadRequest_FailInvalidInputPhoto(String values) {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "11";
        userRegistrationRequest.email = "111111@111.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = values;
        //act
        ResponseEntity<Object> expected = ResponseEntity.
                badRequest().body("Bad entry data");
        ResponseEntity<Object> result = userController.
                createNewUser(userRegistrationRequest);
        //assert
        assertEquals(expected, result);
    }

    //Get projects of given user endpoint tests

    @Test
    void getProjectsUserIsCurrentlyAllocated() {
        //arrange
        String email = "necapantufa@isep.pt";

        ProjectDto projectDto = new ProjectDto();
        List<ProjectDto> projectDtoList = new ArrayList<>();

        projectDtoList.add(projectDto);

        when(projectsUserIsAllocatedService.getProjectsUserIsCurrentlyAllocated(email)).thenReturn(projectDtoList);


        //act

        ResponseEntity<Object> result = userController.getProjectsUserIsCurrentlyAllocated(email);

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(projectDtoList);

        //assert

        assertEquals(expected, result);
    }

    @Test
    void getProjectsUserIsCurrentlyAllocatedEmptyList() {

        //arrange
        String email = "necapantufa@isep.pt";


        List<ProjectDto> projectDtoList = new ArrayList<>();


        when(projectsUserIsAllocatedService.getProjectsUserIsCurrentlyAllocated(email)).thenReturn(projectDtoList);

        //act

        ResponseEntity<Object> result = userController.getProjectsUserIsCurrentlyAllocated(email);

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.NO_CONTENT).contentType(MediaType.APPLICATION_JSON).body(projectDtoList);

        //assert

        assertEquals(expected,result);
    }

    //Update User Profile endpoint tests

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n", "\r"})
    void shouldFail_BadData(String values) {
        //arrange
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.profileID = values;
        editUserDTO.userID = 1;
        //act
        ResponseEntity<Object> expected = ResponseEntity.badRequest()
                .body("Bad entry data");
        ResponseEntity<Object> result = userController.
                updateUserProfile(editUserDTO);
        //assert
        assertEquals(expected, result);
    }

    @Test
    void shouldFail_noUser() {
        //arrange
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.profileID = "test";
        editUserDTO.userID = 1;
        when(updateUserProfileService.profileUpdate(editUserDTO)).
                thenReturn(Optional.empty());
        //act
        ResponseEntity<Object> expected = ResponseEntity.badRequest()
                .body("User or Profile doesn't exist with these IDs");
        ResponseEntity<Object> result = userController.
                updateUserProfile(editUserDTO);
        //assert
        assertEquals(expected, result);
    }

    @Test
    void shouldFail_nullDTO() {
        //arrange

        //act
        ResponseEntity<Object> expected = ResponseEntity.badRequest()
                .body("Bad entry data");
        ResponseEntity<Object> result = userController.
                updateUserProfile(null);
        //assert
        assertEquals(expected, result);
    }

    @Test
    void shouldSucceed_noUser() {
        //arrange
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.profileID = "test";
        editUserDTO.userID = 1;
        UserUiDTO userUiDTO= mock(UserUiDTO.class);
        when(updateUserProfileService.profileUpdate(editUserDTO)).
                thenReturn(Optional.of(userUiDTO));
        //act
        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.CREATED).
                body(Optional.of(userUiDTO));
        ResponseEntity<Object> result = userController.
                updateUserProfile(editUserDTO);
        //assert
        assertEquals(expected, result);
    }

    //Edit user endpoint tests

    @Test
    void editUserInfoController_success() {
        //Arrange
        EditUserDTO editUserDTO = mock(EditUserDTO.class);
        editUserDTO.userEmail = "joao@gmail.com";
        UserUIInfoDTO userUIInfoDTO = mock(UserUIInfoDTO.class);
        when(editUserService.setNewUserData(editUserDTO))
                .thenReturn(Optional.of(userUIInfoDTO));

        ResponseEntity<Object> expected =
                ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(userUIInfoDTO);
        //act
        ResponseEntity<Object> result =
                userController.editUserInfo(editUserDTO);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void editUserInfoController_badEntryData_fail() {
        //Arrange
        EditUserDTO editUserDTO = mock(EditUserDTO.class);
        editUserDTO.userEmail=" ";

        UserUIInfoDTO userUIInfoDTO = mock(UserUIInfoDTO.class);

        when(editUserService.setNewUserData(editUserDTO)).thenReturn
                (Optional.of(userUIInfoDTO));

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus
                .BAD_REQUEST).body("Bad entry data or email is missing");

        //act
        ResponseEntity<Object> result = userController
                .editUserInfo(editUserDTO);

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void editUserInfoController_noUserFound_fail() {
        //Arrange
        EditUserDTO editUserDTO = mock(EditUserDTO.class);
        editUserDTO.userEmail = "fail@gmail.com";
        when(editUserService.setNewUserData(editUserDTO))
                .thenReturn(Optional.empty());
        ResponseEntity<Object> expected = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No User found with that email address");
        //act
        ResponseEntity<Object> result = userController
                .editUserInfo(editUserDTO);
        //Assert
        assertEquals(expected, result);
    }

    //Activate user endpoint tests

    @Test
    void activateUser_UnitTest_SuccessScenario(){

        //Arrange
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.userID = 1;
        editUserDTO.activation = true;

        when(activateNewUserService.activateNewUser(1)).thenReturn(true);

        ResponseEntity<String> expected = new ResponseEntity<>("User was successfully activated.", HttpStatus.OK);

        //Act
        ResponseEntity<Object> result = userController.activateNewUser(editUserDTO);

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void activateUser_UnitTest_FailureScenario_UserIsNotFound(){

        //Arrange
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.userID = 1;
        editUserDTO.activation = true;

        when(activateNewUserService.activateNewUser(1)).thenReturn(false);
        ResponseEntity<String> expected = new ResponseEntity<>("User was not found.", HttpStatus.NOT_FOUND);

        //Act
        ResponseEntity<Object> result = userController.activateNewUser(editUserDTO);

        //Assert
        assertEquals(expected, result);
    }

    //Search user endpoint tests

    @Test
    void searchUserByEmailGreatSuccess() {

        //arrange
        UserUiDTO userDTO = mock(UserUiDTO.class);

        when(viewUsersService.getUserByEmail("email@email.com")).thenReturn(Optional.of(userDTO));


        ResponseEntity<Object> expected = ResponseEntity.ok(Optional.of(userDTO));

        //act
        ResponseEntity<Object> actual = userController.searchUserByEmail("email@email.com");

        //assert

        assertEquals(expected, actual);
    }

    @Test
    void searchUserByEmailMegaFailure() {

        //arrange
        UserUiDTO userDTO = mock(UserUiDTO.class);

        when(viewUsersService.getUserByEmail("email@email.com")).thenReturn(Optional.empty());


        ResponseEntity<Object> expected = ResponseEntity.badRequest().body("User not found");

        //act
        ResponseEntity<Object> actual = userController.searchUserByEmail("email@email.com");

        //assert

        assertEquals(expected, actual);
    }

    @Test
    void searchUserByProfileFound() {

        UserUiDTO userDTO1 = mock(UserUiDTO.class);
        UserUiDTO userDTO2 = mock(UserUiDTO.class);

        List<UserUiDTO> userList = new ArrayList<>();
        userList.add(userDTO1);
        userList.add(userDTO2);

        when(viewUsersService.getUserByProfile("Boss")).thenReturn(userList);

        ResponseEntity expected = ResponseEntity.ok(userList);


        //act
        ResponseEntity actual = userController.searchUserByProfile("Boss");


        //assert
        assertEquals(expected, actual);


    }

    @Test
    void searchUserByProfileNtFound() {
        List<UserUiDTO> userList = new ArrayList<>();


        when(viewUsersService.getUserByProfile("Boss")).thenReturn(userList);

        ResponseEntity expected = ResponseEntity.badRequest().body("User not found");


        //act
        ResponseEntity actual = userController.searchUserByProfile("Boss");


        //assert
        assertEquals(expected, actual);


    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "", "\t", "\n", "\r"})
    void changeUserPasswordBadRequest(String values) {

        EditUserDTO editUserDTO = mock(EditUserDTO.class);

        editUserDTO.newPassword = values;
        editUserDTO.oldPassword = values;
        editUserDTO.userID = 1;

        int userID = editUserDTO.userID;


        userController.updatePassword(editUserDTO);

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No User found with that ID");

        ResponseEntity<Object> result = userController.updatePassword(editUserDTO);

        assertEquals(expected, result);

    }

    @Test
    void changePasswordEmailNotFound() {

        when(changeUserPasswordService.setNewPassword(any())).thenReturn(Optional.empty());

        EditUserDTO editUserDTO = new EditUserDTO();

        editUserDTO.newPassword = "newPass";
        editUserDTO.oldPassword = "oldPass";
        editUserDTO.userID = 1;


        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No User found with that ID");

        ResponseEntity<Object> result = userController.updatePassword(editUserDTO);

        assertEquals(expected, result);

    }

    @Test
    void changePasswordNull() {

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Bad entry data");

        ResponseEntity<Object> result = userController.updatePassword(null);

        assertEquals(expected, result);

    }

    @Test
    void setChangePasswordSuccess() {

        UserUIInfoDTO outEditedUserInfoDTO = mock(UserUIInfoDTO.class);

        when(changeUserPasswordService.setNewPassword(any())).thenReturn(Optional.of(outEditedUserInfoDTO));

        EditUserDTO editUserDTO = new EditUserDTO();

        editUserDTO.newPassword = "newPass";
        editUserDTO.oldPassword = "oldPass";
        editUserDTO.userID = 1;

        ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                .body(outEditedUserInfoDTO);


        ResponseEntity<Object> result = userController.updatePassword(editUserDTO);

        assertEquals(expected, result);

    }

    @Test
    void shouldReturnListOfUsers() {

        // Arrange

        UserUiDTO userUiDTOMock = mock(UserUiDTO.class);

        List<UserUiDTO> userUiDTOList = new ArrayList<>();

        userUiDTOList.add(userUiDTOMock);

        when(viewUsersService.getAllUsers()).thenReturn(userUiDTOList);

        ResponseEntity<Object> respondeEntityExpected = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(userUiDTOList);

        // Act

        ResponseEntity<Object> responseEntityResult = userController.getAllUsers();

        // Assert

        assertEquals(respondeEntityExpected, responseEntityResult);
    }

    @Test
    void shouldReturnEmptyListOfUsers() {

        // Arrange

        List<UserUiDTO> userUiDTOList = new ArrayList<>();

        when(viewUsersService.getAllUsers()).thenReturn(userUiDTOList);

        ResponseEntity<Object> respondeEntityExpected = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"There are currently no Users created\"}");

        // Act

        ResponseEntity<Object> responseEntityResult = userController.getAllUsers();

        // Assert

        assertEquals(respondeEntityExpected, responseEntityResult);
    }

    @Test
    void shouldReturnUserById() {


        // Arrange

        int userId = 1;

        UserUIInfoDTO userUIInfoDTOMock = mock(UserUIInfoDTO.class);

        Optional<UserUIInfoDTO> userUIInfoDTOOptional = Optional.of(userUIInfoDTOMock);

        when(viewUsersService.getUserByID(userId)).thenReturn(userUIInfoDTOOptional);

        ResponseEntity<Object> responseEntityExpected =  ResponseEntity.ok(userUIInfoDTOOptional);

        // Act

        ResponseEntity<Object> responseEntityResult = userController.searchUserByID(userId);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void shouldNotReturnUserById() {


        // Arrange

        int userId = 1;

        when(viewUsersService.getUserByID(userId)).thenReturn(Optional.empty());

        ResponseEntity<Object> responseEntityExpected =  ResponseEntity
                .badRequest()
                .body("User not found");

        // Act

        ResponseEntity<Object> responseEntityResult = userController.searchUserByID(userId);

        // Assert

        assertEquals(responseEntityExpected, responseEntityResult);
    }

    @Test
    void testPatchUser_badInput(){

        EditUserDTO editUserDTO = new EditUserDTO();

        ResponseEntity expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body("Forbidden operation, validate parameters");

        ResponseEntity result = userController.patchUser(editUserDTO);

        assertEquals(expected, result);
    }

    @Test
    void testPatchUser_activate(){

        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.userID = 1;
        editUserDTO.activation = true;
        editUserDTO.action = "ActivateUser";

        ResponseEntity expected = new ResponseEntity<>("User was successfully activated.", HttpStatus.OK);
        when(activateNewUserService.activateNewUser(1)).thenReturn(true);

        ResponseEntity result = userController.patchUser(editUserDTO);

        assertEquals(expected, result);
    }

    @Test
    void testPatchUser_editUserInfo(){

        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.userID = 1;
        editUserDTO.function ="function";
        editUserDTO.photo = "url";
        editUserDTO.userEmail = "email@email.com";
        editUserDTO.action = "EditFunction";

        ResponseEntity expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body("No User found with that email address");
        when(editUserService.setNewUserData(editUserDTO)).thenReturn(Optional.empty());
        ResponseEntity result = userController.patchUser(editUserDTO);

        assertEquals(expected, result);
    }



}