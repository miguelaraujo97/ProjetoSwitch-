package switchisep.project.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import switchisep.project.datamodel.UserJpa;
import switchisep.project.datamodel.assemblers.UserDomainDataAssembler;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.*;
import switchisep.project.repositories.jpa.UserJpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @InjectMocks
    UserRepository userRepository;
    @Mock
    UserDomainDataAssembler userDomainDataAssembler;
    @Mock
    UserJpaRepository userJpaRepository;

    @Test
    void testSaveUser(){

        //Arrange
        User userToBeSaved = mock(User.class);
        UserJpa userJpaToBeSaved = mock(UserJpa.class);
        UserJpa userJpaSaved = mock(UserJpa.class);

        when(userDomainDataAssembler.toData(userToBeSaved)).thenReturn(userJpaToBeSaved);

        when(userJpaRepository.save(userJpaToBeSaved)).thenReturn(userJpaSaved);

        when(userDomainDataAssembler.toDomain(userJpaSaved)).thenReturn(userToBeSaved);

        //Act
        User userSaved = userRepository.save(userToBeSaved);

        //Assert
        assertEquals(userToBeSaved, userSaved);
    }

    @Test
    void testFindAllUsers(){

        //Arrange
        List<UserJpa> listToBeFound = new ArrayList<>();
        List<User> listToBeCreated= new ArrayList<>();

        UserJpa userJpaInList = mock(UserJpa.class);
        listToBeFound.add(userJpaInList);

        when(userJpaRepository.findAll()).thenReturn(listToBeFound);
        User userInList = mock(User.class);
        listToBeCreated.add(userInList);

        when(userDomainDataAssembler.toDomain(userJpaInList)).thenReturn(userInList);

        //Act
        List<User> listCreated = userRepository.findAll();

        //Assert
        assertEquals(listToBeCreated, listCreated);
    }

    @Test
    void findByEmail_exists(){
        //Arrange
        UserJpa userJpaFoundByEmail = mock(UserJpa.class);
        User userFoundByEmail = mock(User.class);
        Email email = Email.createEmail("teste@email.com");

        Optional<UserJpa> opUserJpa = Optional.of(userJpaFoundByEmail);

        when(userJpaRepository.findByEmail(email)).thenReturn(opUserJpa);
        when(userDomainDataAssembler.toDomain(userJpaFoundByEmail)).thenReturn(userFoundByEmail);

        //Act
        Optional<User> opUser = userRepository.findByEmail(email);

        //Assert
        assertEquals(opUser, Optional.of(userFoundByEmail));
    }


    @Test
    void findByEmail_doesNotExist(){

        //Arrange
        Email email = Email.createEmail("teste@email.com");
        Optional<UserJpa> opUserJpa = Optional.empty();
        when(userJpaRepository.findByEmail(email)).thenReturn(opUserJpa);

        //Act
        Optional<User> opUser = userRepository.findByEmail(email);

        //Assert
        assertEquals(opUser, Optional.empty());
    }

    @Test
    void findByID_exists(){
        //Arrange
        UserJpa userJpaFoundByID = mock(UserJpa.class);
        User userFoundByID = mock(User.class);
        UserID id = UserID.createUserID(1);

        Optional<UserJpa> opUserJpa = Optional.of(userJpaFoundByID);

        when(userJpaRepository.findById(id)).thenReturn(opUserJpa);
        when(userDomainDataAssembler.toDomain(userJpaFoundByID)).thenReturn(userFoundByID);

        //Act
        Optional<User> opUser = userRepository.findById(id);

        //Assert
        assertEquals(opUser, Optional.of(userFoundByID));
    }


    @Test
    void findByID_doesNotExist(){

        //Arrange
        UserID id = UserID.createUserID(1);
        Optional<UserJpa> opUserJpa = Optional.empty();
        when(userJpaRepository.findById(id)).thenReturn(opUserJpa);

        //Act
        Optional<User> opUser = userRepository.findById(id);

        //Assert
        assertEquals(opUser, Optional.empty());
    }

    @Test
    void deleteAll(){
        userRepository.deleteAll();
        userJpaRepository.deleteAll();
        assertEquals(userJpaRepository.findAll().size(), 0);
        assertEquals(userRepository.findAll().size(), 0);

    }

    @Test
    void findByProfileID(){
        //Arrange
        List<UserJpa> listToBeFound = new ArrayList<>();
        List<User> listToBeCreated= new ArrayList<>();

        UserJpa userJpaInList = mock(UserJpa.class);
        listToBeFound.add(userJpaInList);

        ProfileID profileID = ProfileID.generateID();

        when(userJpaRepository.findByProfileID(profileID)).thenReturn(listToBeFound);
        User userInList = mock(User.class);
        listToBeCreated.add(userInList);

        when(userDomainDataAssembler.toDomain(userJpaInList)).thenReturn(userInList);

        //Act
        List<User> listCreated = userRepository.findByProfileID(profileID);

        //Assert
        assertEquals(listToBeCreated, listCreated);
    }



}