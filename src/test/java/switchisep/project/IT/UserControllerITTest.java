package switchisep.project.IT;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import switchisep.project.dto.*;
import switchisep.project.dto.assemblers.UserDomainInternalDTOAssembler;
import switchisep.project.controllers.ResourceController;
import switchisep.project.controllers.ProjectController;
import switchisep.project.controllers.UserController;
import switchisep.project.domain.profile.Profile;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ProfileRepository;
import switchisep.project.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerITTest {
    @Autowired
    UserController userController;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDomainInternalDTOAssembler userToUiDTOAssembler;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    ProjectController projectController;
    @Autowired
    ResourceController resourceController;


    //Create user endpoint tests
    @Test
    void createUserSuccess_mockMVC() throws Exception {
        //Arrange

        //There must be a visitor profile in the Profile Repository
        Profile defaultProfile = new Profile(Profile.DEFAULT_PROFILE_NAME);
        profileRepository.save(defaultProfile);

        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "jose";
        userRegistrationRequest.email = "112341111@111.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "https://banco.bankinter.pt" +
                "/particulares/images/logo01.gif";

        UserUIInfoDTO userUIInfoDTO = new UserUIInfoDTO();

        userUIInfoDTO.name  = userRegistrationRequest.userName;
        userUIInfoDTO.email = userRegistrationRequest.email;
        userUIInfoDTO.function = userRegistrationRequest.function;
        userUIInfoDTO.photo = userRegistrationRequest.photo;

        //act
        MvcResult result = mockMvc.perform(post(
                        "/users/")
                        .content(objectMapper.writeValueAsString(
                                userRegistrationRequest))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isCreated()).andReturn();
        String mvcResult = result.getResponse().getContentAsString();

        //assert
        assertEquals(201, result.getResponse().getStatus());

        profileRepository.deleteAll();
    }

    @Test
    void createUserFail_BadEntryData_mockMVC() throws Exception {
        //arrange
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "";
        userRegistrationRequest.email = "111121212111@111.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "https://banco.bankinter.pt" +
                "/particulares/images/logo01.gif";

        //act
        MvcResult result = mockMvc.perform(post(
                        "/users/")
                        .content(objectMapper.writeValueAsString(
                                userRegistrationRequest))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest()).andReturn();
        String mvcResult = result.getResponse().getContentAsString();
        //assert
        assertEquals("Bad entry data", mvcResult);
    }

    @Test
    void createUserFail_UserIdAlreadyExists_mockMVC() throws Exception {
        //Arrange

        //There must be a visitor profile in the Profile Repository
        Profile defaultProfile = new Profile(Profile.DEFAULT_PROFILE_NAME);
        profileRepository.save(defaultProfile);
        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();
        UserRegistrationRequest userRegistrationRequest1 =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "jose";
        userRegistrationRequest.email = "11121221111@111.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "https://banco.bankinter.pt" +
                "/particulares/images/logo01.gif";

        userRegistrationRequest1.userName = "jose";
        userRegistrationRequest1.email = "11121221111@111.com";
        userRegistrationRequest1.function = "111";
        userRegistrationRequest1.password = "1111";
        userRegistrationRequest1.photo = "https://banco.bankinter.pt" +
                "/particulares/images/logo01.gif";

        //act
        mockMvc.perform(post("/users/")
                        .content(objectMapper.writeValueAsString(
                                userRegistrationRequest))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isCreated()).andReturn();

        MvcResult result = mockMvc.perform(post("/users/")
                        .content(objectMapper.writeValueAsString(
                                userRegistrationRequest1))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest()).andReturn();
        String mvcResult = result.getResponse().getContentAsString();
        //assert
        assertEquals("Email already exists", mvcResult);
        profileRepository.deleteAll();
    }

    //Update profile endpoint tests
    @Test
    void updateFail_UserDoesntExists_mockMVC() throws Exception {
        //arrange
        EditUserDTO editUserDTO = new EditUserDTO();
        editUserDTO.profileID = "Visitor";
        editUserDTO.userID = 100;
        editUserDTO.action = "ProfileUpdate";

        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "jose";
        userRegistrationRequest.email = "11121221111@111.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "https://banco.bankinter.pt" +
                "/particulares/images/logo01.gif";


        //act


        MvcResult result = mockMvc.perform(patch("/users")
                        .content(objectMapper.writeValueAsString(
                                editUserDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isBadRequest()).andReturn();
        String mvcResult = result.getResponse().getContentAsString();
        //assert
        assertEquals("User or Profile doesn't exist with these IDs", mvcResult);
    }

    //Search user endpoint tests

    @Test
    void searchUserByEmailSuccessfully() throws Exception {

        //Arrange
        String emailToSearch = "amado@me.com";
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("amado@me.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        ProfileID profileId = ProfileID.generateID();
        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileId);
        userRepository.save(user);
        UserUiDTO userUiDTO = userToUiDTOAssembler.toOutputDTO(user);
        Link link = linkTo(methodOn(UserController.class).
                searchUserByID(userUiDTO.userId)).withSelfRel();
        userUiDTO.add(link);

        //Act
        MvcResult result = mockMvc.perform(get("/users/by-email/{email}", emailToSearch)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String content = result.getResponse().getContentAsString();

        //Assert
        assertEquals(objectMapper.writeValueAsString(userUiDTO), content);
        userRepository.deleteAll();
    }

    @Test
    void searchUserByEmailUnsuccessfully() throws Exception {

        //Arrange
        String emailToSearch = "francisco@me.com";
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("amado@me.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        Profile profile = new Profile(Name.createName("Developer"));
        ProfileID profileId = ProfileID.generateID();
        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileId);
        userRepository.save(user);

        //Act
        MvcResult result = mockMvc.perform(get("/users/by-email/{email}", emailToSearch)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        String content = result.getResponse().getContentAsString();

        //Assert
        assertEquals("User not found", content);
        userRepository.deleteAll();
    }

    @Test
    void searchUserByProfileSuccessfully() throws Exception {

        //Arrange
        String profileToSearch = "Visitor";
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("amado@me.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        Profile profile = new Profile(Name.createName("Visitor"));
        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profile.getProfileID());
        userRepository.save(user);
        profileRepository.save(profile);

        //Act & assert
        mockMvc.perform(get("/users/by-profile/{profile}", profileToSearch)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        userRepository.deleteAll();
        profileRepository.deleteAll();
    }

    @Test
    void searchUserByProfileUnsuccessfully() throws Exception {

        //Arrange
        String profileToSearch = "Administrator";
        UserName userName = UserName.createUserName("UserNameTest");
        Email email = Email.createEmail("amado@me.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("Developer");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("passwordForTesting");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        Profile profile = new Profile(Name.createName("Developer"));
        Profile profile1 = new Profile(Name.createName("Administrator"));
        ProfileID profileId = ProfileID.generateID();

        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileId);
        userRepository.save(user);
        profileRepository.save(profile);
        profileRepository.save(profile1);

        //Act
        MvcResult result = mockMvc.perform(get("/users/by-profile/{profile}", profileToSearch)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

        String content = result.getResponse().getContentAsString();

        //Assert
        assertEquals("User not found", content);
        userRepository.deleteAll();
        profileRepository.deleteAll();
    }

    @Test
    void changePasswordNotFound() throws Exception {

        EditUserDTO editUserDTO = new EditUserDTO();

        editUserDTO.userID = 1;
        editUserDTO.oldPassword = "oldPass";
        editUserDTO.newPassword = "newPass";
        editUserDTO.action = "PasswordUpdate";

        userController.updatePassword(editUserDTO);


        MvcResult result = mockMvc.perform(patch("/users")
                        .content(objectMapper.writeValueAsString(
                                editUserDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isNotFound()).andReturn();
        String mvcResult = result.getResponse().getContentAsString();
        //assert
        assertEquals("No User found with that ID", mvcResult);
    }


   /* @Test
    void changePasswordSuccess() throws Exception {


        UserName userName = UserName.createUserName("jose");
        Email email = Email.createEmail("mail@mail.com");
        UserID userId = UserID.createUserID(1);
        Function function = Function.createFunction("111");
        PasswordHash hashedPassword = PasswordHash.createHashPassword("oldPass");
        Photo photo = Photo.createPhoto("https://www.isep.ipp.pt/img/logo3.png");
        Profile profile = new Profile(Name.createName("Visitor"));
        ProfileID profileId = ProfileID.generateID();
        User user = new User(userId, userName, email,
                function, hashedPassword, photo, profileId);
        profileRepository.save(profile);
        userRepository.save(user);

        ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO();

        changePasswordDTO.userID = 1;
        changePasswordDTO.oldPassword = "oldPass";
        changePasswordDTO.newPassword = "newPass";



        MvcResult result = mockMvc.perform(patch("/users/updatepassword")
                        .content(objectMapper.writeValueAsString(
                                changePasswordDTO))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();
        String mvcResult = result.getResponse().getContentAsString();

        ChangePasswordToUIDTO outEditedUserInfoDTO = new ChangePasswordToUIDTO();
        userController.updatePassword(changePasswordDTO);
        ChangePasswordToUIDTO outEditedUserInfoDTO = new ChangePasswordToUIDTO();

        outEditedUserInfoDTO.userID = 1;
        outEditedUserInfoDTO.userEmail = "mail@mail.com";
        outEditedUserInfoDTO.userName = "jose";
        outEditedUserInfoDTO.function = "111";
        outEditedUserInfoDTO.photo = "https://www.isep.ipp.pt/img/logo3.png";

        Link link = linkTo(WebMvcLinkBuilder.methodOn(UserController.class).SearchUserByID(changePasswordDTO.userID)).withRel((LinkRelation) userController).withType("PATCH");

        outEditedUserInfoDTO.add(link);

        //assert
        assertEquals(objectMapper.writeValueAsString(outEditedUserInfoDTO), mvcResult);
    }


    //------------------------------------------------------------------------------------------------------//

    //US017
    @Test
    void getProjectsUserIsCurrentlyAllocated() throws Exception {

        //Arrange

        //There must be a visitor profile in the Profile Repository
        Profile defaultProfile = new Profile(Profile.DEFAULT_PROFILE_NAME);
        profileRepository.save(defaultProfile);

        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "jose";
        userRegistrationRequest.email = "necapantufa@isep.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "https://banco.bankinter.pt" +
                "/particulares/images/logo01.gif";

        userController.createNewUser(userRegistrationRequest);

        ResourceDTO resourceDTO = new ResourceDTO("A1234566", "necapantufa@isep.com",
                LocalDate.of(1234, 12, 12),
                LocalDate.of(1234, 12, 25), 10,
                30, "Developer");

        addResourceToProjectController.addResourceToProject(resourceDTO);

        ProjectDto projectDto = new ProjectDto();
        projectDto.code = "A1234566";
        projectDto.name = "Integration test";
        projectDto.customer = "ISEP test";

        projectController.createProject(projectDto);

        //act and assert
        MvcResult result = mockMvc.perform(get(
                        "/users/{userId}/projects", userRegistrationRequest.email)
                        .content(objectMapper.writeValueAsString(
                                userRegistrationRequest))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isOk()).andReturn();

        assertEquals(200, result.getResponse().getStatus());


    }

    @Test
    void getProjectsUserIsCurrentlyAllocatedEmptyList() throws Exception {

        //Arrange

        //There must be a visitor profile in the Profile Repository
        Profile defaultProfile = new Profile(Profile.DEFAULT_PROFILE_NAME);
        profileRepository.save(defaultProfile);

        UserRegistrationRequest userRegistrationRequest =
                new UserRegistrationRequest();

        userRegistrationRequest.userName = "jose";
        userRegistrationRequest.email = "necapantufa@isep.com";
        userRegistrationRequest.function = "111";
        userRegistrationRequest.password = "1111";
        userRegistrationRequest.photo = "https://banco.bankinter.pt" +
                "/particulares/images/logo01.gif";

        userController.createNewUser(userRegistrationRequest);

        //act and assert
        MvcResult result = mockMvc.perform(get(
                        "/users/{userId}/projects", userRegistrationRequest.email)
                        .content(objectMapper.writeValueAsString(
                                userRegistrationRequest))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType("application/json"))
                .andExpect(status().isNoContent()).andReturn();



       assertEquals(204, result.getResponse().getStatus());


    }

    }*/
}