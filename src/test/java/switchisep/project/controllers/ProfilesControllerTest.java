package switchisep.project.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import switchisep.project.dto.ProfileDTO;
import switchisep.project.domain.valueobjects.ProfileID;
import switchisep.project.services.CreateProfileService;
import switchisep.project.services.ViewProfilesService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProfilesControllerTest {
    @InjectMocks
    ProfilesController profilesController;

    @InjectMocks
    ProfileDTO profileDTO;

    @Mock
    CreateProfileService createProfileService;

    @Mock
    ViewProfilesService viewProfilesService;

    @Test
    void createProfile_should_succeed() {
        profileDTO.name = "MegaBoss";
        profileDTO.profileID = ProfileID.generateID().getProfileID();

        when(createProfileService.createAndSaveProfile(profileDTO))
                .thenReturn(Optional.of(profileDTO));

        ResponseEntity<Object> expected =
                ResponseEntity.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(profileDTO);


        ResponseEntity<Object> actual =
                profilesController.createProfile(profileDTO);

        assertEquals(expected, actual);

    }

    @Test
    void createProfile_shouldFail_DuplicatedProfile() {
        profileDTO.name = "Test Purposes";

        when(createProfileService.createAndSaveProfile(profileDTO))
                .thenReturn(Optional.empty());

        ResponseEntity<Object> expected =ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"Profile already " +
                        "exists.\"}");

        ResponseEntity<Object> actual = profilesController.createProfile(profileDTO);

        assertEquals(expected,actual);
    }

//    @Test
//    void createProfile_shouldFail_EmptyInput() {
//        newProfileDTO.name = "";
//
//        ResponseEntity<Object> expected = ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body("{\"error\":\"400\",\"message\":\"You have to " +
//                        "insert a profile name!\"}");
//
//        ResponseEntity<Object> actual =
//                createProfileController.createProfile(newProfileDTO);
//
//        assertEquals(expected,actual);
//    }

//    @Test
//    void createProfile_shouldFail_InvalidName() {
//        newProfileDTO.name = "abc1";
//
//        when(createProfileService.createAndSaveProfile(newProfileDTO))
//                .thenThrow(IllegalArgumentException.class);
//
//        ResponseEntity<Object> expected =ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body("{\"error\":\"409\",\"message\":\"Make sure that " +
//                        "your" +
//                        " input value contains only letters.\"}");
//
//        ResponseEntity<Object> actual = createProfileController.createProfile(newProfileDTO);
//
//        System.out.println(actual);
//        assertEquals(expected,actual);
//    }

    @Test
    void shouldReturnEmptyListOfProfiles() {

        // Arrange

        List<ProfileDTO> profileDTOList = new ArrayList<>();

        when(viewProfilesService.getAllProfiles()).thenReturn(profileDTOList);

        ResponseEntity<Object> expected = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"There are no created Profiles.\"}");

        // Act

        ResponseEntity<Object> responseEntityResult = profilesController.getAllProfiles();

        // Assert

        assertEquals(expected, responseEntityResult);
    }

    @Test
    void shouldReturnListOfProfiles() {

        // Arrange

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.name = "Teste";
        profileDTO.profileID = "PF-01";

        List<ProfileDTO> profileDTOList = new ArrayList<>();

        profileDTOList.add(profileDTO);

        when(viewProfilesService.getAllProfiles()).thenReturn(profileDTOList);

        ResponseEntity<Object> expected = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileDTOList);

        // Act

        ResponseEntity<Object> responseEntityResult = profilesController.getAllProfiles();

        // Assert

        assertEquals(expected, responseEntityResult);
    }

    @Test
    void shouldReturnProfileById() {

        // Arrange

        String profileId = "PF-01";

        ProfileDTO profileDTO = new ProfileDTO();

        profileDTO.name = "Teste";
        profileDTO.profileID = "PF-01";

        Optional<ProfileDTO> profileDTOOptional = Optional.of(profileDTO);

        when(viewProfilesService.getProfileById(profileId)).thenReturn(profileDTOOptional);

        ResponseEntity<Object> expected = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileDTO);

        // Act

        ResponseEntity<Object> responseEntityResult = profilesController.getProfileById(profileId);

        // Assert

        assertEquals(expected, responseEntityResult);
    }

    @Test
    void shouldNotReturnProfileByID() {

        // Arrange

        String profileId = "PF-01";

        when(viewProfilesService.getProfileById(profileId)).thenReturn(Optional.empty());

        ResponseEntity<Object> expected = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"No Profile found with that ID.\"}");

        // Act

        ResponseEntity<Object> responseEntityResult = profilesController.getProfileById(profileId);

        // Assert

        assertEquals(expected, responseEntityResult);
    }
}