package switchisep.project.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import switchisep.project.datamodel.ProjectJpa;
import switchisep.project.datamodel.assemblers.ProjectDomainDataAssembler;
import switchisep.project.domain.project.Project;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.repositories.jpa.ProjectJpaRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectRepositoryTest {

    @InjectMocks
    ProjectRepository projectRepository;

    @Mock
    ProjectJpaRepository projectJpaRepository;

    @Mock
    ProjectDomainDataAssembler projectDomainDataAssembler;

    @Test
    void shouldReturnListOfProjectCodes() {

        // Arrange

        ProjectJpa projectJpaMock = mock(ProjectJpa.class);

        Project projectMock = mock(Project.class);

        ProjectCode projectCode = ProjectCode.createProjectCode("A123");

        List<ProjectCode> projectCodesList = new ArrayList<>();

        projectCodesList.add(projectCode);

        List<ProjectJpa> projectJpaList = new ArrayList<>();

        projectJpaList.add(projectJpaMock);

        List<Project> expectedProjectList = new ArrayList<>();

        expectedProjectList.add(projectMock);

        when(projectJpaRepository.findAllById(projectCodesList)).thenReturn(projectJpaList);

        when(projectDomainDataAssembler.toDomain(projectJpaMock)).thenReturn(projectMock);

        // Act

        List<Project> projectListResult = projectRepository.getAllProjectsByProjectCode(projectCodesList);

        // Assert
        assertEquals(expectedProjectList, projectListResult);
    }

}