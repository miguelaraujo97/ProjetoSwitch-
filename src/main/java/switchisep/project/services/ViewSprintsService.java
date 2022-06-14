package switchisep.project.services;

import org.springframework.stereotype.Service;
import switchisep.project.dto.SprintUIDTO;
import switchisep.project.dto.assemblers.SprintUIDTOAssembler;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.SprintID;
import switchisep.project.repositories.interfaces.SprintRepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViewSprintsService {

    private SprintRepositoryInterface sprintRepositoryInterface;

    private SprintUIDTOAssembler sprintUIDTOAssembler;

    public ViewSprintsService(
            SprintRepositoryInterface sprintRepositoryInterface,
                              SprintUIDTOAssembler sprintUIDTOAssembler) {
        this.sprintRepositoryInterface = sprintRepositoryInterface;
        this.sprintUIDTOAssembler = sprintUIDTOAssembler;
    }

    public List<SprintUIDTO> getSprintsByProjectCode(String projectCode) {

        ProjectCode projectCodeVO = ProjectCode.createProjectCode(projectCode);

        List<SprintUIDTO> sprintUIDTOList = new ArrayList<>();

        List<Sprint> sprintList = sprintRepositoryInterface.findAll();

        for (Sprint sprint : sprintList) {

            if(sprint.getProjectCode().equals(projectCodeVO)) {
                SprintUIDTO sprintUIDTO = sprintUIDTOAssembler.toDTO(sprint);
                sprintUIDTOList.add(sprintUIDTO);
            }
        }

        return sprintUIDTOList;
    }

    public Optional<SprintUIDTO> getSprintByID(String id) {

        SprintID sprintID = SprintID.createSprintID(id);
        Optional<Sprint> optionalSprint = sprintRepositoryInterface.findBySprintID(sprintID);

        if (!optionalSprint.isPresent()) {
            return Optional.empty();
        }

        SprintUIDTO sprintDTO = sprintUIDTOAssembler.toDTO(optionalSprint.get());

        return Optional.of(sprintDTO);
    }
}
