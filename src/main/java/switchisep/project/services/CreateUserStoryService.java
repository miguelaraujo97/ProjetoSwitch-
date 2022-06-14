package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.NewUserStoryInfo;
import switchisep.project.dto.UserStoryDTO;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.domain.domainservices.CreateUserStoryDomainService;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.IUserStoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CreateUserStoryService {

    private final IUserStoryRepository userStoryRepository;

    private final ProjectRepository projectRepository;

    private final UserStoryDomainDTOAssembler userStoryAssembler;

    private final CreateUserStoryDomainService createUserStoryDomainService;

    @Autowired
    public CreateUserStoryService(IUserStoryRepository userStoryRepository,
                                  ProjectRepository projectRepository,
                                  UserStoryDomainDTOAssembler userStoryAssembler,
                                  CreateUserStoryDomainService createUserStoryDomainService) {

        this.userStoryRepository = userStoryRepository;
        this.projectRepository = projectRepository;
        this.userStoryAssembler = userStoryAssembler;
        this.createUserStoryDomainService = createUserStoryDomainService;
    }

    public Optional<UserStoryDTO> createAndSaveUserStory(NewUserStoryInfo newUserStoryInfo) {

        UserStoryID userStoryID = UserStoryID.createUserStoryID();

        ProjectCode projectCode = ProjectCode.createProjectCode(newUserStoryInfo.projectCode);

        boolean projectExists = projectRepository.existsProjectByProjectCode(projectCode);

        if (projectExists) {

            List<UserStory> productBacklog = userStoryRepository.findAllByProjectCode(projectCode);

            Optional<UserStory> optionalUserStory = createUserStoryDomainService.
                    createUserStory(newUserStoryInfo.description, projectCode, productBacklog, userStoryID);

            if (optionalUserStory.isPresent()) {

                UserStory userStory = optionalUserStory.get();

                userStoryRepository.save(userStory);

                UserStoryDTO optionalUserStoryDTO = userStoryAssembler.fromDomainToDTO(userStory);

                return Optional.of(optionalUserStoryDTO);
            }
        }

        return Optional.empty();
    }

}
