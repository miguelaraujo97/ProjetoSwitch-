package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.ProjectDto;
import switchisep.project.dto.UpdateProjectDto;
import switchisep.project.dto.assemblers.ProjectDtoAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.valueobjects.ProjectCode;

import switchisep.project.repositories.ProjectRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditProjectServiceTest {

    @InjectMocks
    EditProjectService editProjectService;

    @Mock
    ProjectRepository projectRepository;

    @Mock
    ProjectDtoAssembler projectDTOAssembler;

    @Test
    void setNewDataSuccess() {

        //Arrange
        ProjectDto projectDTO = mock(ProjectDto.class);

        UpdateProjectDto setNewData = mock(UpdateProjectDto.class);
        setNewData.code = "a123";
        setNewData.startDate = LocalDate.of(2022, 02, 25);
        setNewData.endDate = LocalDate.of(2026, 03, 22);
        setNewData.sprintDuration = 10;
        setNewData.numberOfPlannedSprints = 15;
        setNewData.status = "Closed";
        setNewData.projectDescription = "change description";

        ProjectCode code = ProjectCode.createProjectCode(setNewData.code);

        Project project = mock(Project.class);
        Project savedProject = mock(Project.class);


        when(projectRepository.getProjectByProjectCode(code)).thenReturn(Optional.of(project));

        when(projectRepository.saveProject(project)).thenReturn(savedProject);

        when(projectDTOAssembler.toNative(savedProject)).thenReturn(projectDTO);



        //act

        Optional<ProjectDto> result = editProjectService.setNewData(setNewData);

        //assert

        assertEquals(Optional.of(projectDTO),result);

    }

    @Test
    void setNewDataProjectNotFound() {

        //Arrange


        UpdateProjectDto setNewData = mock(UpdateProjectDto.class);

        setNewData.code = "a123";
        ProjectCode code = ProjectCode.createProjectCode(setNewData.code);

        when(projectRepository.getProjectByProjectCode(code)).thenReturn(Optional.empty());

        //act

        Optional<ProjectDto> result = editProjectService.setNewData(setNewData);

        //assert

        assertEquals(Optional.empty(),result);

    }

    @Test
    void setNewDataEmpty() {

        //Arrange
        ProjectDto projectDTO = mock(ProjectDto.class);

        UpdateProjectDto setNewData = mock(UpdateProjectDto.class);
        setNewData.code = "a123";
        setNewData.status = null;

        ProjectCode code = ProjectCode.createProjectCode(setNewData.code);

        Project project = mock(Project.class);
        Project savedProject = mock(Project.class);

        when(projectRepository.getProjectByProjectCode(code)).thenReturn(Optional.of(project));

        when(projectRepository.saveProject(project)).thenReturn(savedProject);

        when(projectDTOAssembler.toNative(savedProject)).thenReturn(projectDTO);

        //act

        Optional<ProjectDto> result = editProjectService.setNewData(setNewData);

        //assert

        assertEquals(Optional.of(projectDTO),result);

    }
}