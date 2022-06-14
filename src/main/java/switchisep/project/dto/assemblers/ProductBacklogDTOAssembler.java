package switchisep.project.dto.assemblers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.*;
import switchisep.project.domain.userstory.UserStory;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductBacklogDTOAssembler {
    @Autowired
    UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;

    private ProductBacklogDTOAssembler(){
        //Empty ---
    }

    public List<UserStoryDTO> toValueObjectDTO(List<UserStory> productBacklog) {
        List<UserStoryDTO> productBacklogDTO = new ArrayList<>();

        for (UserStory userStory : productBacklog) {
            productBacklogDTO.add(userStoryDomainDTOAssembler.fromDomainToDTO(userStory));
        }
        return productBacklogDTO;
    }
}
