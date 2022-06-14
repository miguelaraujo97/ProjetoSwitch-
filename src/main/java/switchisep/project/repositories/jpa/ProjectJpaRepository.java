package switchisep.project.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import switchisep.project.datamodel.ProjectJpa;
import switchisep.project.domain.valueobjects.ProjectCode;



@Service
public interface ProjectJpaRepository extends JpaRepository<ProjectJpa, ProjectCode> {

    /// see why java.lang.IllegalStateException: Failed to load ApplicationContext
    //Optional<ProjectJpa> findByProjectCode(ProjectCode projectCode);



}
