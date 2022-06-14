package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.UserStoryDTO;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.IUserStoryRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


@Service
public class ProductBacklogSortedByPriorityService {

    private final IUserStoryRepository userStoryRepository;
    private final UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;
    private final ProjectRepository projectRepository;

    public ProductBacklogSortedByPriorityService(
            IUserStoryRepository userStoryRepository,
            UserStoryDomainDTOAssembler userStoryDomainDTOAssembler,
            ProjectRepository projectRepository) {

        this.userStoryRepository = userStoryRepository;
        this.projectRepository = projectRepository;
        this.userStoryDomainDTOAssembler = userStoryDomainDTOAssembler;
    }

    public Optional<List<UserStoryDTO>> getProductBacklogSortedByPriority(String code) {

        ProjectCode projectCode = ProjectCode.createProjectCode(code);
        List<UserStoryDTO> sortedProductBacklogDto = new ArrayList<>();

        if (!projectRepository.existsProjectByProjectCode(projectCode)) {
            return Optional.empty();
        } else {
            List<UserStory> productBacklog =
                    userStoryRepository.findAllByProjectCode(projectCode);
            //Method to sort productBacklog by priority
            productBacklog.sort(Comparator.comparing(UserStory::getPriority));

            // Foreach to convert us into usDTO
            for (UserStory userStory : productBacklog) {
                userStoryDomainDTOAssembler.fromDomainToDTO(userStory);
                sortedProductBacklogDto.add(userStoryDomainDTOAssembler.
                        fromDomainToDTO(userStory));
            }
            return Optional.of(sortedProductBacklogDto);
        }

    }


}
