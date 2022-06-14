package switchisep.project.repositories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.datamodel.TypologyJpa;
import switchisep.project.datamodel.assemblers.TypologyDomainDataAssembler;
import switchisep.project.domain.typology.Typology;
import switchisep.project.domain.user.User;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;
import switchisep.project.repositories.jpa.TypologyJPARepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TypologyRepositoryTest {

    @InjectMocks
    TypologyRepository typologyRepository;
    @Mock
    TypologyDomainDataAssembler typologyDomainDataAssembler;
    @Mock
    TypologyJPARepository typologyJPARepository;



    @Test
    void save() {
        Typology typologyToBeSaved = mock(Typology.class);
        TypologyJpa typologyJpaToBeSaved = mock(TypologyJpa.class);
        TypologyJpa savedTypologyStoryJpa = mock(TypologyJpa.class);

        when(typologyDomainDataAssembler.toData(typologyToBeSaved)).thenReturn(typologyJpaToBeSaved);
        when(typologyJPARepository.save(typologyJpaToBeSaved)).thenReturn(savedTypologyStoryJpa);
        when(typologyDomainDataAssembler.toDomain(savedTypologyStoryJpa)).thenReturn(typologyToBeSaved);
        // Act
        Optional<Typology> savedTypology = typologyRepository.save(typologyToBeSaved);
        // Assert
        assertEquals(Optional.of(typologyToBeSaved), savedTypology);
    }

    @Test
    void existsByTypologyDescription() {
        User userFoundByEmail = mock(User.class);
        Description desc = mock(Description.class);



        when(typologyJPARepository.existsByTypologyDescription(desc)).thenReturn(true);

        //Act

        //Assert
        assertTrue(typologyRepository.existsByTypologyDescription(desc));
    }

    @Test
    void findTypologyById() {
        // Arrange
        TypologyID typologyID = TypologyID.createTypologyID();
        String typologyIdGET = typologyID.getTypologyID();
        Optional<TypologyJpa> typologyJpa = Optional.empty();
        when(typologyJPARepository.findById(typologyID)).thenReturn(typologyJpa);
        // Act
        Optional<TypologyJpa> result = typologyJPARepository.findById(typologyID);
        // Assert
        assertEquals(Optional.empty(), result);
    }

    @Test
    void findAllTypologies() {
        //Arrange
        List<TypologyJpa> listJpa = new ArrayList<>();
        List<Typology> listTypologies = new ArrayList<>();

        Typology typologyMock = mock(Typology.class);

        TypologyJpa typologyJpa = mock(TypologyJpa.class);

        listJpa.add(typologyJpa);
        listTypologies.add(typologyMock);

        when(typologyJPARepository.findAll()).thenReturn(listJpa);
        when(typologyDomainDataAssembler.toDomain(any(TypologyJpa.class))).thenReturn(typologyMock);

        //Act

        List<Typology> result = typologyRepository.findAllTypologies();

        //Assert
        assertEquals(listTypologies, result);
    }

    @Test
    void shouldReturnTypologyByID() {

        // Arrange

        TypologyID typologyID = TypologyID.createTypologyIdWithString("TYPO-01");

        Typology typologyMock = mock(Typology.class);

        TypologyJpa typologyJpaMock = mock(TypologyJpa.class);

        Optional<TypologyJpa> typologyJpaOptional = Optional.of(typologyJpaMock);

        when(typologyJPARepository.findById(typologyID)).thenReturn(typologyJpaOptional);

        when(typologyDomainDataAssembler.toDomain(typologyJpaMock)).thenReturn(typologyMock);

        Optional<Typology> typologyOptionalExpected = Optional.of(typologyMock);

        // Act

        Optional<Typology> typologyOptionalResult = typologyRepository.findTypologyById(typologyID);

        // Assert

        assertEquals(typologyOptionalExpected, typologyOptionalResult);
    }

    @Test
    void shouldNotReturnTypologyById() {

        // Arrange

        TypologyID typologyID = TypologyID.createTypologyIdWithString("TYPO-01");


        when(typologyJPARepository.findById(typologyID)).thenReturn(Optional.empty());

        // Act

        Optional<Typology> typologyOptionalResult = typologyRepository.findTypologyById(typologyID);

        // Assert

        assertEquals(Optional.empty(), typologyOptionalResult);
    }
}