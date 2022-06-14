package switchisep.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.SprintJpa;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.SprintID;

import java.util.List;
import java.util.Optional;

@Repository
public interface SprintJpaRepository extends JpaRepository<SprintJpa,
        SprintID> {

    Optional<SprintJpa> findBySprintID(SprintID sprintID);

    List<SprintJpa> findByProjectCode(ProjectCode projectCode);



}
