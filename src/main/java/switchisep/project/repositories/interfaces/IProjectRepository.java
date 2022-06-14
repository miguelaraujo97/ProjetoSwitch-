package switchisep.project.repositories.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import switchisep.project.datamodel.ProjectJpa;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.valueobjects.ProjectCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public interface IProjectRepository {

    boolean existsProjectByProjectCode(ProjectCode code);

    Project saveProject(Project project);

    Optional<Project> getProjectByProjectCode(ProjectCode projectCode);

    Page<Project> getAllProjects(Pageable pageable);

    void deleteAll();

    Iterable<ProjectJpa> findAll();


    long count();


    List<Project> getAllProjectsByProjectCode(List<ProjectCode> projectCodes);


}
