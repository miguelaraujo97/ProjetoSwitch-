package switchisep.project.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.datamodel.SprintJpa;
import switchisep.project.datamodel.assemblers.SprintDomainDataAssembler;
import switchisep.project.domain.sprint.Sprint;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.SprintID;
import switchisep.project.repositories.jpa.SprintJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SprintRepositoryTest {
    @InjectMocks
    SprintRepository sprintRepository;
    @Mock
    SprintJpaRepository sprintJpaRepository;
    @Mock
    SprintDomainDataAssembler sprintDomainDataAssembler;

    @Test
    void findAll() {
        //Arrange
        Sprint sprintMock = mock(Sprint.class);
        SprintJpa sprintJpaMock = mock(SprintJpa.class);

        List<Sprint> expectedSprintList = new ArrayList<>();
        List<SprintJpa> listJPA = new ArrayList<>();

        expectedSprintList.add(sprintMock);
        listJPA.add(sprintJpaMock);

        when(sprintDomainDataAssembler.toDomain(sprintJpaMock)).thenReturn(sprintMock);
        when(sprintJpaRepository.findAll()).thenReturn(listJPA);

        //Act
        List<Sprint> resultSprintList = sprintRepository.findAll();

        //Assert
        assertEquals(expectedSprintList, resultSprintList);
    }

    @Test
    void save(){
        //Arrange
        Sprint sprintMock = mock(Sprint.class);
        SprintJpa sprintJpaMock = mock(SprintJpa.class);

        when(sprintDomainDataAssembler.toData(sprintMock)).thenReturn(sprintJpaMock);
        when(sprintJpaRepository.save(sprintJpaMock)).thenReturn(sprintJpaMock);
        when(sprintDomainDataAssembler.toDomain(sprintJpaMock)).thenReturn(sprintMock);

        //Act
        Sprint result = sprintRepository.save(sprintMock);

        //Assert
        assertEquals(sprintMock, result);
    }

    @Test
    void findByProject(){
        //Arrange
        ProjectCode projectCode = ProjectCode.createProjectCode("A12");

        Sprint sprintMock = mock(Sprint.class);
        SprintJpa sprintJpaMock = mock(SprintJpa.class);

        List<Sprint> expectedSprintList = new ArrayList<>();
        List<SprintJpa> listJPA = new ArrayList<>();

        expectedSprintList.add(sprintMock);
        listJPA.add(sprintJpaMock);

        when(sprintJpaRepository.findAll()).thenReturn(listJPA);
        when(sprintDomainDataAssembler.toDomain(any(SprintJpa.class))).thenReturn(sprintMock);
        when(sprintJpaMock.getProjectCode()).thenReturn(projectCode);

        //Act
        List<Sprint> resultSprintList = sprintRepository.findSprintsByProjectCode(projectCode);

        //Assert
        assertEquals(expectedSprintList, resultSprintList);
    }

    @Test
    void findBySprintID_Found(){
        //Arrange
        SprintID sprintID = SprintID.createSprintID("SP001");

        Sprint sprintMock = mock(Sprint.class);
        SprintJpa sprintJpaMock = mock(SprintJpa.class);


        when(sprintJpaRepository.findBySprintID(sprintID)).thenReturn(Optional.of(sprintJpaMock));
        when(sprintDomainDataAssembler.toDomain(sprintJpaMock)).thenReturn(sprintMock);

        //Act
        Optional<Sprint> result = sprintRepository.findBySprintID(sprintID);

        //Assert
        assertEquals(Optional.of(sprintMock), result);
    }

    @Test
    void findBySprintID_NotFound(){
        //Arrange
        SprintID sprintID = SprintID.createSprintID("SP001");

        when(sprintJpaRepository.findBySprintID(sprintID)).thenReturn(Optional.empty());

        //Act
        Optional<Sprint> result = sprintRepository.findBySprintID(sprintID);

        //Assert
        assertEquals(Optional.empty(), result);
    }


}