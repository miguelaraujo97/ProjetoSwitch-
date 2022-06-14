package switchisep.project.repositories.interfaces;

import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.SprintID;

import java.util.List;
import java.util.Optional;

public interface SprintRepositoryInterface {

    Sprint save(Sprint sprint);

    List<Sprint> findAll();

    List<Sprint> findSprintsByProjectCode(ProjectCode projectCode);

    Optional<Sprint> findBySprintID(SprintID id);



}
