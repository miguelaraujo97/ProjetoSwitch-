package switchisep.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import switchisep.project.dto.ProjectDto;
import switchisep.project.dto.assemblers.ProjectDtoAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.ResourceRepository;
import switchisep.project.repositories.interfaces.IProjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectsUserIsAllocatedService {


    ResourceRepository resourceRepository;
    IProjectRepository projectRepository;
    ProjectDtoAssembler projectDtoAssembler;

    @Autowired
    public ProjectsUserIsAllocatedService(ResourceRepository resourceRepository, IProjectRepository projectRepository, ProjectDtoAssembler projectDtoAssembler) {

        this.resourceRepository = resourceRepository;
        this.projectRepository = projectRepository;
        this.projectDtoAssembler = projectDtoAssembler;
    }


    public List<ProjectDto> getProjectsUserIsCurrentlyAllocated(String email) {

        List<ProjectCode> projectCodeList = resourceRepository.findAllProjectsCodeByEmail(email);

        List<Project> projectList = projectRepository.getAllProjectsByProjectCode(projectCodeList);

        List<ProjectDto> projectDtoList = new ArrayList<>();

        for (Project project : projectList) {
            ProjectDto projectDto = projectDtoAssembler.toNative(project);
            projectDtoList.add(projectDto);
        }

        return projectDtoList;
    }


}
