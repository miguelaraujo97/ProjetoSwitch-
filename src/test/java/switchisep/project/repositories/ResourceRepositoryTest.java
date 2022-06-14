package switchisep.project.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import switchisep.project.datamodel.ResourceJpa;
import switchisep.project.datamodel.assemblers.ResourceDomainDataAssembler;
import switchisep.project.domain.resource.Resource;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.jpa.ResourceJpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResourceRepositoryTest {

    @InjectMocks
    ResourceRepository resourceRepository;
    @Mock
    ResourceJpaRepository resourceJpaRepository;

    @Mock
    ResourceDomainDataAssembler resourceDomainDataAssembler;

    @Test
    void successfullyFindResourceByEmail() {

        //Arrange
        Resource resource = mock(Resource.class);
        ResourceJpa resourceJpa = mock(ResourceJpa.class);
        String email = "amado@me.com";
        resourceRepository.save(resource);
        List<ResourceJpa> expectedJpa = new ArrayList<>();
        expectedJpa.add(resourceJpa);
        when(resourceJpaRepository.findByEmail(email)).thenReturn(expectedJpa);
        int sizeExpected = expectedJpa.size();

        //Act
        int result = resourceRepository.findByEmail(email).size();

        //Assert
        assertEquals(result, sizeExpected);
    }

    @Test
    void findNoResourcesByEmail() {

        //Arrange
        String email = "souza@hotmail.com";

        //Act
        List<Resource> result = resourceRepository.findByEmail(email);

        //Assert
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void successfullyAddResourceToList() {

        //Arrange
        Resource resource = mock(Resource.class);

        ResourceJpa resourceJpa = mock(ResourceJpa.class);

        //Act

        Resource result = resourceRepository.save(resource);

        //Assert
        assertEquals(resource, result);
    }

    @Test
    void findByRoleAndProjectCodeSuccessfully_endDateIsAfterProvidedDate() {

        //Arrange
        ResourceJpa expected = mock(ResourceJpa.class);
        String role = "Developer";
        String projectCode = "A12345";
        LocalDate dateToCompare = LocalDate.of(2024, 11, 25);
        when(resourceJpaRepository.findByRoleAndEndDateIsAfterAndProjectCode(role, dateToCompare, projectCode)).
                thenReturn(Optional.of(expected));

        //Act
        Optional<ResourceJpa> actual = resourceRepository.findByRoleAndEndDateIsAfterAndProjectCode(
                role, dateToCompare, projectCode);

        //Assert
        assertEquals(actual.get(), expected);
    }

    @Test
    void findByRoleAndProjectCodeUnsuccessfully() {

        //Arrange
        String role = "Developer";
        String projectCode = "A12345";
        LocalDate dateToCompare = LocalDate.of(2025, 11, 25);

        //Act
        Optional<ResourceJpa> actual = resourceRepository.findByRoleAndEndDateIsAfterAndProjectCode(
                role, dateToCompare, projectCode);

        //Assert
        assertEquals(actual, Optional.empty());
    }

    @Test
    void findByEmailAndProjectCodeSuccessfully_endDateIsAfterProvidedDate() {

        //Arrange
        ResourceJpa expected = mock(ResourceJpa.class);
        String email = "amado@me.com";
        String projectCode = "A12345";
        LocalDate dateToCompare = LocalDate.of(2024, 11, 25);
        when(resourceJpaRepository.findByEmailAndEndDateAfterAndProjectCode(email, dateToCompare, projectCode)).
                thenReturn(Optional.of(expected));

        //Act
        Optional<ResourceJpa> actual = resourceRepository.findByEmailAndEndDateAfterAndProjectCode(
                email, dateToCompare, projectCode);

        //Assert
        assertEquals(actual.get(), expected);
    }

    @Test
    void findByEmailAndProjectCodeUnsuccessfully() {

        //Arrange
        String email = "amado@me.com";
        String projectCode = "A12345";
        LocalDate dateToCompare = LocalDate.of(2025, 12, 25);

        //Act
        Optional<ResourceJpa> actual = resourceRepository.findByEmailAndEndDateAfterAndProjectCode(
                email, dateToCompare, projectCode);

        //Assert
        assertEquals(actual, Optional.empty());
    }

    @Test
    void editEndDateSuccessfully() {

        //Arrange
        ResourceJpa expected = mock(ResourceJpa.class);
        LocalDate startDate = LocalDate.of(2024, 12, 25);
        LocalDate newEndDate = LocalDate.of(2026, 12, 25);
        when(expected.getStartDate()).thenReturn(startDate);
        doNothing().when(expected).setEndDate(newEndDate);
        when(resourceJpaRepository.save(expected)).thenReturn(expected);

        //Act
        ResourceJpa actual = resourceRepository.editEndDate(expected, newEndDate);

        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void findAllProjectCodesByEmailSuccessfully() {

        //Arrange
        List<ProjectCode> projectCodeListExpected = new ArrayList<>();
        ResourceJpa resourceJpa = mock(ResourceJpa.class);
        String email = "amado@me.com";
        String projectCode = "A12345";
        when(resourceJpa.getProjectCode()).thenReturn(projectCode);
        projectCodeListExpected.add(ProjectCode.createProjectCode(projectCode));
        List<ResourceJpa> resourceJpaList = new ArrayList<>();
        resourceJpaList.add(resourceJpa);
        when(resourceJpaRepository.findByEmail(email)).thenReturn(resourceJpaList);

        //Act
        List<ProjectCode> projectCodeListActual = resourceRepository.findAllProjectsCodeByEmail(email);

        //Assert
        assertEquals(projectCodeListExpected, projectCodeListActual);
    }

    @Test
    void findNoProjectCodesByEmail() {

        //Arrange
        String emailToSearch = "souza@gmail.com";
        when(resourceJpaRepository.findByEmail(emailToSearch)).thenReturn(Collections.emptyList());

        //Act
        List<ProjectCode> projectCodeListActual = resourceRepository.findAllProjectsCodeByEmail(emailToSearch);

        //Assert
        assertEquals(Collections.emptyList(), projectCodeListActual);
    }

    @Test
    void shouldReturnResourcesByProjectCodeList() {

        // Arrange

        String projectCode = "A123";

        Resource resourceMock = mock(Resource.class);

        List<Resource> resourceList = new ArrayList<>();

        resourceList.add(resourceMock);

        ResourceJpa resourceJpaMock = mock(ResourceJpa.class);

        List<ResourceJpa> resourceJpaList = new ArrayList<>();

        resourceJpaList.add(resourceJpaMock);

        when(resourceJpaRepository.findAllByProjectCode(projectCode)).thenReturn(resourceJpaList);

        when(resourceDomainDataAssembler.toDomain(resourceJpaMock)).thenReturn(resourceMock);

        // Act

        List<Resource> resourceListResult = resourceRepository.findAllByProjectCode(projectCode);

        // Assert

        assertEquals(resourceList, resourceListResult);
    }

    @Test
    void shouldReturnResourceById() {

        // Arrange

        String resourceId = "RC-01";

        ResourceJpa resourceJpaMock = mock(ResourceJpa.class);

        Optional<ResourceJpa> resourceJpaOptional = Optional.of(resourceJpaMock);

        Resource resourceMock = mock(Resource.class);

        Optional<Resource> resourceOptional = Optional.of(resourceMock);

        when(resourceJpaRepository.findByResourceID(resourceId)).thenReturn(resourceJpaOptional);

        when(resourceDomainDataAssembler.toDomain(resourceJpaMock)).thenReturn(resourceMock);

        // Act

        Optional<Resource> resourceOptionalResult = resourceRepository.findById(resourceId);

        // Assert

        assertEquals(resourceOptional, resourceOptionalResult);
    }

    @Test
    void shouldNotReturnResourceById() {

        // Arrange

        String resourceId = "RC-01";

        when(resourceJpaRepository.findByResourceID(resourceId)).thenReturn(Optional.empty());

        // Act

        Optional<Resource> resourceOptionalResult = resourceRepository.findById(resourceId);

        // Assert

        assertEquals(Optional.empty(), resourceOptionalResult);
    }

}