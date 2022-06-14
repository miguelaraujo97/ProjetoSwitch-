package switchisep.project.services;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import switchisep.project.dto.ProjectDto;
import switchisep.project.dto.assemblers.ProjectDtoAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.valueobjects.ProjectCode;

import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.interfaces.IProjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class ViewProjectService {

    private final ProjectDtoAssembler projectDtoAssembler;

    private final IProjectRepository projectRepository;


    public ViewProjectService(IProjectRepository projectRepository, ProjectDtoAssembler projectDtoAssembler) {
        this.projectRepository = projectRepository;
        this.projectDtoAssembler = projectDtoAssembler;
    }

    /**
     * Method to get a list of  projects .
     * @authors Joao Reis and Ricardo Pereira
     * @return list of projectDto.

     */
    public Page<ProjectDto> getAllProjects(Pageable paging) {
        List<ProjectDto> projectDtoList = new ArrayList<>();

        Page<Project> pageResponse = projectRepository.getAllProjects(paging);

        List<Project> projectList = pageResponse.getContent();
        for (Project project : projectList) {
            ProjectDto projectDto = projectDtoAssembler.toNative(project);
            projectDtoList.add(projectDto);
        }

        Page<ProjectDto> pageProjectDto = new PageImpl<ProjectDto>(projectDtoList, pageResponse.getPageable(), pageResponse.getTotalElements());

        return pageProjectDto;
    }

    /**
     * Method to get a project by is ID (projectCode).
     *
     * @param projectCode ProjectCode
     * @return Optional ProjectDto in case of success  or an Optional empty otherwise.
     * @authors Joao Reis and Ricardo Pereira
     */
    public Optional<ProjectDto> getProjectByCode(String projectCode) {
        ProjectCode code = ProjectCode.createProjectCode(projectCode);

        Optional<Project> projectOpt = projectRepository.getProjectByProjectCode(code);

        if (projectOpt.isPresent()) {

            ProjectDto projectDto = projectDtoAssembler.toNative(projectOpt.get());

            return Optional.of(projectDto);
        } else

            return Optional.empty();
    }
}
