package switchisep.project.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.datamodel.UserStoryJpa;
import switchisep.project.datamodel.assemblers.UserStoryDomainDataAssembler;
import switchisep.project.domain.userstory.UserStory;
import switchisep.project.domain.valueobjects.ProjectCode;
import switchisep.project.domain.valueobjects.UserStoryID;
import switchisep.project.repositories.jpa.UserStoryJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserStoryRepositoryTest {

    @InjectMocks
    UserStoryRepository userStoryRepository;

    @Mock
    UserStoryDomainDataAssembler userStoryDomainDataAssembler;

    @Mock
    UserStoryJpaRepository userStoryJpaRepository;

    @Test
    void shouldSaveUserStory() {

        // Arrange

        UserStory userStoryToBeSaved = mock(UserStory.class);

        UserStoryJpa userStoryJpaToBeSaved = mock(UserStoryJpa.class);

        UserStoryJpa savedUserStoryJpa = mock(UserStoryJpa.class);

        when(userStoryDomainDataAssembler.toData(userStoryToBeSaved)).thenReturn(userStoryJpaToBeSaved);

        when(userStoryJpaRepository.save(userStoryJpaToBeSaved)).thenReturn(savedUserStoryJpa);

        when(userStoryDomainDataAssembler.toDomain(savedUserStoryJpa)).thenReturn(userStoryToBeSaved);

        // Act

        UserStory savedUserStory = userStoryRepository.save(userStoryToBeSaved);

        // Assert
        assertEquals(userStoryToBeSaved, savedUserStory);
    }

    @Test
    void shouldReturnUserStoryListByProjectCode() {

        // Arrange

        String projectCodeString = "A123";

        ProjectCode projectCode = ProjectCode.createProjectCode(projectCodeString);

        UserStoryJpa userStoryJpa = mock(UserStoryJpa.class);

        List<UserStoryJpa> userStoryJpaListToBeFound = new ArrayList<>();

        userStoryJpaListToBeFound.add( userStoryJpa );

        UserStory userStory = mock(UserStory.class);

        List<UserStory> userStoryListToBeCreated = new ArrayList<>();

        userStoryListToBeCreated.add( userStory );

        when(userStoryJpaRepository.findAllByProjectCode(projectCodeString)).thenReturn(userStoryJpaListToBeFound);

        when(userStoryDomainDataAssembler.toDomain(userStoryJpa)).thenReturn(userStory);

        // Act

        List<UserStory> result = userStoryRepository.findAllByProjectCode(projectCode);

        // Assert
        assertEquals(userStoryListToBeCreated, result);
    }

    @Test
    void shouldReturnUserStoryById() {

        // Arrange

        UserStoryJpa userStoryJpaFoundById = mock(UserStoryJpa.class);

        UserStory userStoryFoundByID = mock(UserStory.class);

        UserStoryID userStoryID = UserStoryID.createUserStoryID();

        String userStoryIdString = userStoryID.getUserStoryID();

        Optional<UserStoryJpa> optionalUserStoryJpa = Optional.of(userStoryJpaFoundById);

        when(userStoryJpaRepository.findByUserStoryID(userStoryIdString)).thenReturn(optionalUserStoryJpa);

        when(userStoryDomainDataAssembler.toDomain(userStoryJpaFoundById)).thenReturn(userStoryFoundByID);

        // Act

        Optional<UserStory> result = userStoryRepository.findByUserStoryID(userStoryID);

        // Assert
        assertEquals(Optional.of(userStoryFoundByID), result);
    }

    @Test
    void shouldNotReturnUserStoryById() {

        // Arrange

        UserStoryID userStoryID = UserStoryID.createUserStoryID();

        String userStoryIdString = userStoryID.getUserStoryID();

        Optional<UserStoryJpa> optionalUserStoryJpa = Optional.empty();

        when(userStoryJpaRepository.findByUserStoryID(userStoryIdString)).thenReturn(optionalUserStoryJpa);

        // Act
        Optional<UserStory> result = userStoryRepository.findByUserStoryID(userStoryID);

        // Assert
        assertEquals(Optional.empty(), result);
    }


}