package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.FullUserStoryDTO;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.UserStoryID;
import switchisep.project.repositories.interfaces.IUserStoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserStoryServiceTest {

    @InjectMocks
    GetUserStoryService getUserStoryService;
    @Mock
    IUserStoryRepository userStoryRepository;

    @Mock
    UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;

    @Mock
    UserStory userStory;

    @Test
    void shouldReturnOptionalOfFullUserStoryDTO() {

        // Arrange

        String userStoryID = "US-f1a6e522-a305-479c-8263-04515e51fb2a";

        String projectCode = "A123";

        UserStoryID userStoryIdVO = UserStoryID.createUserStoryIdWithString(userStoryID);

        when(userStoryRepository.findByUserStoryID
                (userStoryIdVO)).thenReturn(Optional.of(userStory));

        FullUserStoryDTO fullUserStoryDTO = new FullUserStoryDTO();

        fullUserStoryDTO.userStoryID = userStoryID;
        fullUserStoryDTO.description = "Teste";
        fullUserStoryDTO.projectCode = projectCode;
        fullUserStoryDTO.priority = 1;
        fullUserStoryDTO.effortEstimate = 0;
        fullUserStoryDTO.userStoryStatus = "PLANNED";
        fullUserStoryDTO.parentUserStory = "";

        when(userStoryDomainDTOAssembler.toFullInfoUserStoryDTO(userStory)).thenReturn(fullUserStoryDTO);

        // Act

        Optional<FullUserStoryDTO> result =
                getUserStoryService.getUserStoryById(userStoryID);

        // Assert

        assertEquals(Optional.of(fullUserStoryDTO), result);
    }

    @Test
    void shouldNotReturnOptionalOfFullUserStoryDTO() {

        // Arrange

        String userStoryID = "US-f1a6e522-a305-479c-8263-04515e51fb2a";

        UserStoryID userStoryIdVO = UserStoryID.createUserStoryIdWithString(userStoryID);

        when(userStoryRepository.findByUserStoryID
                (userStoryIdVO)).thenReturn(Optional.empty());

        // Act

        Optional<FullUserStoryDTO> result =
                getUserStoryService.getUserStoryById(userStoryID);

        // Assert

        assertEquals(Optional.empty(), result);
    }



}