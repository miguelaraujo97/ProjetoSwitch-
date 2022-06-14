package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ProjectDto;
import switchisep.project.dto.assemblers.ProjectDtoAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.repositories.ProjectRepository;
import switchisep.project.repositories.ResourceRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectsUserIsAllocatedServiceTest {

    @InjectMocks
    ProjectsUserIsAllocatedService projectsUserIsAllocatedService;

    @Mock
    ResourceRepository resourceRepository;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    ProjectDtoAssembler projectDtoAssembler;

    @Test
    void getProjectsUserIsCurrentlyAllocated(){

       //arrange

        Project project = mock(Project.class);

        ProjectCode projectCodeA = ProjectCode.createProjectCode("A1");

        String email = "necapantufa@isep.pt";

        List<Project> projectList = new ArrayList<>();

        projectList.add(project);;

        List<ProjectCode> projectCodeList = new ArrayList<>();

       projectCodeList.add(projectCodeA);

       ProjectDto projectDto = mock(ProjectDto.class);

       List<ProjectDto> projectDtoList = new ArrayList<>();

       projectDtoList.add(projectDto);


        when(resourceRepository.findAllProjectsCodeByEmail(email)).thenReturn(projectCodeList);

        when(projectRepository.getAllProjectsByProjectCode(projectCodeList)).thenReturn(projectList);

        when(projectDtoAssembler.toNative(project)).thenReturn(projectDto);

        //act

        List<ProjectDto> projectDtoListResult = projectsUserIsAllocatedService.getProjectsUserIsCurrentlyAllocated(email);

        //assert

        assertEquals(projectDtoList, projectDtoListResult);

    }


}