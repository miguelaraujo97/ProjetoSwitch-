package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.SprintCreationInfo;
import switchisep.project.dto.SprintUIDTO;
import switchisep.project.dto.assemblers.SprintUIDTOAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.domainservices.CreateSprintDomainService;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.sprint.SprintFactoryInterface;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.error_handling.BusinessRulesException;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.SprintRepositoryInterface;

import java.util.List;
import java.util.Optional;

@Service
public class CreateSprintService {

    SprintRepositoryInterface sprintRepository;
    CreateSprintDomainService createSprintDomainService;
    SprintFactoryInterface sprintFactoryInterface;
    SprintUIDTOAssembler sprintUIDTOAssembler;
    ProjectRepository projectRepository;

    @Autowired
    public CreateSprintService(ProjectRepository projectRepository,
                               SprintRepositoryInterface sprintRepository,
                               CreateSprintDomainService createSprintDomainService,
                               SprintFactoryInterface sprintFactoryInterface,
                               SprintUIDTOAssembler sprintUIDTOAssembler) {
        this.projectRepository = projectRepository;
        this.sprintRepository = sprintRepository;
        this.createSprintDomainService = createSprintDomainService;
        this.sprintFactoryInterface = sprintFactoryInterface;
        this.sprintUIDTOAssembler = sprintUIDTOAssembler;
    }

    public SprintUIDTO createAndSaveSprint(SprintCreationInfo sprintCreationInfo, String projectCode) {

        ProjectCode projectCodeVO = ProjectCode.createProjectCode(projectCode);

        Optional<Project> opProject = projectRepository.getProjectByProjectCode(projectCodeVO);

        if (!opProject.isPresent()) {
            throw new BusinessRulesException("No project was found with this project code");
        }

        Project associatedProject = opProject.get();

        List<Sprint> sprintListByProjectID = sprintRepository.findSprintsByProjectCode(associatedProject.getProjectCode());

        Sprint newSprint = createSprintDomainService.createSprint(sprintCreationInfo,
                associatedProject, sprintListByProjectID, sprintFactoryInterface);

        Sprint sprintSaved = sprintRepository.save(newSprint);

        return sprintUIDTOAssembler.toDTO(sprintSaved);
    }


}
