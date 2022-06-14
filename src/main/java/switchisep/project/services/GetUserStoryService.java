
package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.FullUserStoryDTO;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.UserStoryID;
import switchisep.project.repositories.interfaces.IUserStoryRepository;

import java.util.Optional;

@Service
public class GetUserStoryService {

    private final IUserStoryRepository userStoryRepository;

    private final UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;

    public GetUserStoryService(IUserStoryRepository userStoryRepository,
                               UserStoryDomainDTOAssembler userStoryDomainDTOAssembler) {

        this.userStoryRepository = userStoryRepository;
        this.userStoryDomainDTOAssembler = userStoryDomainDTOAssembler;
    }

    /**
     * Method that goes into the User Story Repository and searches for a specific User Story
     *
     * @param userStoryId The identification Number of the User Story
     * @return Optional with a DTO that contains all the information of the selected User Story.
     */

    public Optional<FullUserStoryDTO> getUserStoryById(String userStoryId) {

        UserStoryID userStoryIdVo = UserStoryID.createUserStoryIdWithString(userStoryId);

        Optional<UserStory> optionalUserStory =
                userStoryRepository.findByUserStoryID( userStoryIdVo);

        if (!optionalUserStory.isPresent()) {

            return Optional.empty();
        }

        FullUserStoryDTO fullUserStoryDTO = userStoryDomainDTOAssembler.toFullInfoUserStoryDTO(optionalUserStory.get());

        return Optional.of(fullUserStoryDTO);

    }
}
