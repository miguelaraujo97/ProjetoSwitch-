package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ProjectDto;
import switchisep.project.dto.assemblers.ProjectDtoAssembler;
import switchisep.project.datamodel.assemblers.ProjectDomainDataAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.repositories.ProjectRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViewProjectServiceTest {

    @InjectMocks
    ViewProjectService viewProjectService;

    @Mock
    ProjectDomainDataAssembler projectDomainDataAssembler;

    @Mock
    ProjectDtoAssembler projectDtoAssembler;

    @Mock
    ProjectRepository projectRepository;

    @Test
    void getProjectByCodeSuccess(){
        //arrange
        String code = "A123";

        ProjectCode projectCode = ProjectCode.createProjectCode(code);

        Project project = mock(Project.class);
        Optional<Project> optProject= Optional.of(project);

        ProjectDto projectDto = mock(ProjectDto.class);

        when(projectRepository.getProjectByProjectCode(projectCode)).thenReturn(optProject);

        when(projectDtoAssembler.toNative(project)).thenReturn(projectDto);

        //act

        Optional<ProjectDto> result =viewProjectService.getProjectByCode(code);

        //assert

        assertEquals(Optional.of(projectDto),result);

    }

    @Test
    void getProjectByCodeNotFound(){
        //arrange
        String code = "A123";

        ProjectCode projectCode = ProjectCode.createProjectCode(code);

        Optional<Project> optProject= Optional.empty();

        when(projectRepository.getProjectByProjectCode(projectCode)).thenReturn(optProject);

        //act

        Optional<ProjectDto> result =viewProjectService.getProjectByCode(code);

        //assert

        assertEquals(Optional.empty(),result);

    }

    /*@Test
    void getAllProjectsSuccess(){
        //arrange
        Project project = mock(Project.class);

        List<Project> projectList = new ArrayList<>();

        projectList.add(project);

        ProjectDto projectDto = mock(ProjectDto.class);

        List<ProjectDto> projectDtos = new ArrayList<>();

        projectDtos.add(projectDto);


        Pageable paging = PageRequest.of(0,10, Sort.by("code"));

        when(projectRepository.getAllProjects(paging)).thenReturn(projectList);

        when(projectDtoAssembler.toNative(project)).thenReturn(projectDto);

        //act

        List<ProjectDto> projectDtoList = viewProjectService.getAllProjects(paging);

        //assert

        assertEquals(projectDtos, projectDtoList);

    }*/


}