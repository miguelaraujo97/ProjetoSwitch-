package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.UsToSprBacklogDTO;
import switchisep.project.dto.UserStoryDTO;
import switchisep.project.dto.assemblers.UserStoryDomainDTOAssembler;
import switchisep.project.domain.domainservices.UsToSprintBacklogDomainService;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.SprintID;
import switchisep.project.domain.valueobjects.UserStoryID;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.IUserStoryRepository;
import switchisep.project.repositories.interfaces.SprintRepositoryInterface;

import java.util.Optional;

@Service
public class AddUsToSprintBacklogService {


    SprintRepositoryInterface sprintRepository;
    IUserStoryRepository userStoryRepository;
    ProjectRepository projectRepository;
    UserStoryDomainDTOAssembler userStoryDomainDTOAssembler;
    UsToSprintBacklogDomainService usToSprintBacklogDomainService;


    public AddUsToSprintBacklogService(SprintRepositoryInterface sprintRepository, IUserStoryRepository userStoryRepository, ProjectRepository projectRepository, UserStoryDomainDTOAssembler userStoryDomainDTOAssembler, UsToSprintBacklogDomainService usToSprintBacklogDomainService) {
        this.sprintRepository = sprintRepository;
        this.userStoryRepository = userStoryRepository;
        this.projectRepository = projectRepository;
        this.userStoryDomainDTOAssembler = userStoryDomainDTOAssembler;
        this.usToSprintBacklogDomainService = usToSprintBacklogDomainService;
    }

    public Optional<UserStoryDTO> addUsToSprintBacklog(
            UsToSprBacklogDTO usToSprBacklogDTO) {
        ProjectCode projectCode = ProjectCode.createProjectCode(
                usToSprBacklogDTO.projectCode);
        SprintID sprintID = SprintID.createSprintID(
                usToSprBacklogDTO.sprintID);
        UserStoryID userStoryID = UserStoryID.createUserStoryIdWithString(
                usToSprBacklogDTO.userStoryID);

        Optional<Project> project = projectRepository.
                getProjectByProjectCode(projectCode);
        Optional<UserStory> userStory = userStoryRepository.
                findByUserStoryID(userStoryID);
        Optional<Sprint> sprint = sprintRepository.
                findBySprintID(sprintID);

        if (!project.isPresent() || !userStory.isPresent() ||
                !sprint.isPresent()) {
            return Optional.empty();
        }


        Optional<UserStory> userStoryToSave =
                usToSprintBacklogDomainService.addToSprintBackLog(
                        project.get(),userStory.get(),
                        sprint.get());
        if (userStoryToSave.isPresent()) {
            UserStory userStorySaved =
                    userStoryRepository.save(userStoryToSave.get());
            UserStoryDTO userStoryDTOSaved =
                    userStoryDomainDTOAssembler.fromDomainToDTO(userStorySaved);
            return Optional.of(userStoryDTOSaved);
        }
        return Optional.empty();
    }

}
