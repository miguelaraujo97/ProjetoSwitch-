package switchisep.project.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import switchisep.project.datamodel.ProjectJpa;
import switchisep.project.datamodel.assemblers.ProjectDomainDataAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.repositories.interfaces.IProjectRepository;
import switchisep.project.repositories.jpa.ProjectJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProjectRepository implements IProjectRepository {

    ProjectJpaRepository projectJpaRepository;
    ProjectDomainDataAssembler projectDomainDataAssembler;

    @Autowired
    public ProjectRepository(ProjectJpaRepository projectJpaRepository, ProjectDomainDataAssembler projectDomainDataAssembler) {
        this.projectJpaRepository = projectJpaRepository;
        this.projectDomainDataAssembler = projectDomainDataAssembler;


    }


    /**
     * @param code identification of project input
     * @return true for unique
     * @author João Reis /Ricardo Pereira
     * Method to check if the project is unique.
     */

    public boolean existsProjectByProjectCode(ProjectCode code) {

        return projectJpaRepository.existsById(code);
    }


    /**
     * Save Project in the repository
     *
     * @param project Project
     * @return Project object id ID
     */

    public Project saveProject(Project project) {

        ProjectJpa projectJpa = projectDomainDataAssembler.toData(project);

        ProjectJpa projectJpaSaved = projectJpaRepository.save(projectJpa);

        return projectDomainDataAssembler.toDomain(projectJpaSaved);

    }


    public Optional<Project> getProjectByProjectCode(ProjectCode projectCode) {

        Optional<ProjectJpa> optionalProjectJpa =
                projectJpaRepository.findById(projectCode);

        if (optionalProjectJpa.isPresent()) {

            Project project = projectDomainDataAssembler.toDomain(optionalProjectJpa.get());

            return Optional.of(project);
        }

        return Optional.empty();

    }

    public Page<Project> getAllProjects(Pageable pageable) {
        List<Project> projectList = new ArrayList<>();

        Page<ProjectJpa> pageProjectJpa = projectJpaRepository.findAll(pageable);

        for (ProjectJpa projectJpa : pageProjectJpa) {
            Project project = projectDomainDataAssembler.toDomain(projectJpa);
            projectList.add(project);
        }

        Page<Project> pageProject = new PageImpl<Project>(projectList, pageProjectJpa.getPageable(), pageProjectJpa.getTotalElements());

        return pageProject;

    }

    public void deleteAll() {
        projectJpaRepository.deleteAll();
    }

    public Iterable<ProjectJpa> findAll() {
        return projectJpaRepository.findAll();
    }

    /*public Optional<Project> findByProjectCode(ProjectCode projectCode) {
        Optional<ProjectJpa> optionalProjectJpa = projectJpaRepository.findByProjectCode(projectCode);

        if (optionalProjectJpa.isPresent()) {
            Project project = projectDomainDataAssembler.toDomain(optionalProjectJpa.get());
            return Optional.of(project);
        }

        return Optional.empty();
    }*/

    public long count() {
        return projectJpaRepository.count();
    }


    public List<Project> getAllProjectsByProjectCode(List<ProjectCode> projectCodes) {

        List<Project> projectList = new ArrayList<>();

        Iterable<ProjectJpa> iterableProjectJpa = projectJpaRepository.findAllById(projectCodes);

        for (ProjectJpa projectJpa : iterableProjectJpa) {
            Project project = projectDomainDataAssembler.toDomain(projectJpa);
            projectList.add(project);
        }

        return projectList;

    }

}
