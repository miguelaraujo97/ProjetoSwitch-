package switchisep.project.dto.assemblers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.UserStoryDTO;
import switchisep.project.domain.userstory.UserStory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductBacklogDTOAssemblerTest {

    @InjectMocks
    ProductBacklogDTOAssembler productBacklogDTOAssembler;

    @Mock
    UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;

    @Test
    void toValueObjectDTO(){

        List<UserStory> inputList = new ArrayList<>();

        List<UserStoryDTO> outputList = new ArrayList<>();

        UserStory userStory = mock(UserStory.class);

        inputList.add(userStory);

        UserStoryDTO userStoryDTO = new UserStoryDTO();

        outputList.add(userStoryDTO);

        when(userStoryDomainDTOAssembler.fromDomainToDTO(userStory)).thenReturn(userStoryDTO);

        List<UserStoryDTO> result = productBacklogDTOAssembler.toValueObjectDTO(inputList);

        assertEquals(outputList, result);



    }

}