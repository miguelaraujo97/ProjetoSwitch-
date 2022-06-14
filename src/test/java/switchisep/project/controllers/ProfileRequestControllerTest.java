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
import switchisep.project.dto.ProfileRequestDTO;
import switchisep.project.dto.UIProfileRequestDTO;
import switchisep.project.services.ProfileRequestService;
import switchisep.project.services.ViewProfileRequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileRequestControllerTest {

    @InjectMocks
    ProfileRequestController profileRequestController;

    @Mock
    ProfileRequestService profileRequestService;

    @Mock
    ViewProfileRequestService viewProfileRequestService;


    @Test
    void getProfileRequest_Success() {
        //arrange
        UIProfileRequestDTO uiProfileRequestDTO = new
                UIProfileRequestDTO("Visitor",
                1);

        ProfileRequestDTO profileRequestDTO = new ProfileRequestDTO(1, "1", 1);

        when(profileRequestService.createAndSaveProfileRequest(
                uiProfileRequestDTO)).thenReturn(Optional.
                of(profileRequestDTO));
        //act
        ResponseEntity<?> expected = ResponseEntity
                                        .status(HttpStatus.OK)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .body(profileRequestDTO);

        ResponseEntity<?> result = profileRequestController.
                profileRequest(uiProfileRequestDTO);
        //assert
        assertEquals(expected, result);

    }

    @Test
    void getProfileRequest_FailIdOrProfileDoenstExist() {
        //arrange
        UIProfileRequestDTO uiProfileRequestDTO = new
                UIProfileRequestDTO("Visitor",
                1);

       // ProfileRequestDTO profileRequestDTO = mock(ProfileRequestDTO.class);

        when(profileRequestService.createAndSaveProfileRequest(
                uiProfileRequestDTO)).thenReturn(Optional.empty());
        //act
        ResponseEntity<?> expected = ResponseEntity.badRequest().body(
                "User Id/Profile doesnt exist");
        ResponseEntity<?> result = profileRequestController.
                profileRequest(uiProfileRequestDTO);
        //assert
        assertEquals(expected, result);

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t", "\n", "\r"})
    void getProfileRequest_ProfileNameFailure(String values) {
        //arrange
        UIProfileRequestDTO uiProfileRequestDTO = new
                UIProfileRequestDTO(values,
                1);
                //act

        ResponseEntity<?> expected = ResponseEntity.badRequest().
                body("Bad entry data");
        ResponseEntity<?> result = profileRequestController.
                profileRequest(uiProfileRequestDTO);
        //assert
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnListOfEmptyProfileRequests() {

        // Arrange

        List<ProfileRequestDTO> profileRequestDTOS = new ArrayList<>();

        ResponseEntity<?> expected = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"There are currently no Profile Requests\"}");

        when(viewProfileRequestService.getAllProfileRequests()).thenReturn(profileRequestDTOS);

        // Act

        ResponseEntity<Object> responseEntityResult = profileRequestController.getAllProfileRequests();

        // Assert

        assertEquals(expected, responseEntityResult);
    }

    @Test
    void shouldReturnListOfProfileRequests() {

        int profileRequestId = 1;

        String userId = "U01";

        int profileId = 1;

        ProfileRequestDTO profileRequestDTO = new ProfileRequestDTO(profileRequestId, userId, profileId);

        List<ProfileRequestDTO> profileRequestDTOS = new ArrayList<>();

        profileRequestDTOS.add(profileRequestDTO);

        ResponseEntity<?> expected = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileRequestDTOS);

        when(viewProfileRequestService.getAllProfileRequests()).thenReturn(profileRequestDTOS);

        // Act

        ResponseEntity<Object> responseEntityResult = profileRequestController.getAllProfileRequests();

        // Assert

        assertEquals(expected, responseEntityResult);
    }

    @Test
    void shouldReturnProfileRequestByID() {

        // Arrange

        int profileRequestId = 1;

        String userId = "U01";

        int profileId = 1;

        ProfileRequestDTO profileRequestDTO = new ProfileRequestDTO(profileRequestId, userId, profileId);

        Optional<ProfileRequestDTO> profileRequestDTOOptional = Optional.of(profileRequestDTO);

        when(viewProfileRequestService.getProfileRequestById(profileRequestId)).thenReturn(profileRequestDTOOptional);

        ResponseEntity<Object> expected = ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileRequestDTO);

        // Act

        ResponseEntity<Object> responseEntityResult = profileRequestController.getProfileRequestById(profileRequestId);

        // Assert

        assertEquals(expected, responseEntityResult);
    }

    @Test
    void shouldNotReturnProfileRequestById() {

        // Arrange

        int profileRequestId = 1;

        String userId = "U01";

        int profileId = 1;

        ProfileRequestDTO profileRequestDTO = new ProfileRequestDTO(profileRequestId, userId, profileId);

        when(viewProfileRequestService.getProfileRequestById(profileRequestId)).thenReturn(Optional.empty());

        ResponseEntity<Object> expected = ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"message\":\"Profile Request not found\"}");

        // Act

        ResponseEntity<Object> responseEntityResult = profileRequestController.getProfileRequestById(profileRequestId);

        // Assert

        assertEquals(expected, responseEntityResult);
    }

}