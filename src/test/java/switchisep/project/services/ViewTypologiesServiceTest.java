package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.TypologyDTO;
import switchisep.project.dto.assemblers.TypologyDomainDTOAssembler;
import switchisep.project.domain.typology.Typology;
import switchisep.project.domain.valueobjects.TypologyID;
import switchisep.project.repositories.interfaces.ITypologyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ViewTypologiesServiceTest {

    @InjectMocks
    ViewTypologiesService viewTypologiesService;

    @Mock
    ITypologyRepository typologyRepository;

    @Mock
    TypologyDomainDTOAssembler typologyDomainDTOAssembler;

    @Test
    void shouldReturnListOfTypologies() {

        // Arrange

        TypologyDTO typologyDTOMock = mock(TypologyDTO.class);

        List<TypologyDTO> typologyDTOList = new ArrayList<>();

        typologyDTOList.add(typologyDTOMock);

        Typology typologyMock = mock(Typology.class);

        List<Typology> typologyList = new ArrayList<>();

        typologyList.add(typologyMock);

        when(typologyRepository.findAllTypologies()).thenReturn(typologyList);

        when(typologyDomainDTOAssembler.toDTO(typologyMock)).thenReturn(typologyDTOMock);

        // Act

        List<TypologyDTO> typologyDTOListResult = viewTypologiesService.getAllTypologies();

        // Assert

        assertEquals(typologyDTOList, typologyDTOListResult);
    }

    @Test
    void shouldReturnTypologyById() {

        // Arrange

        String typologyId = "TY-01";

        TypologyID typologyIdVo = TypologyID.createTypologyIdWithString(typologyId);

        Typology typologyMock = mock(Typology.class);

        Optional<Typology> typologyOptional = Optional.of(typologyMock);

        TypologyDTO typologyDTOMock = mock(TypologyDTO.class);

        Optional<TypologyDTO> typologyDTOOptional = Optional.of(typologyDTOMock);

        when(typologyRepository.findTypologyById(typologyIdVo)).thenReturn(typologyOptional);

        when(typologyDomainDTOAssembler.toDTO(typologyMock)).thenReturn(typologyDTOMock);

        // Act

        Optional<TypologyDTO> typologyDTOOptionalResult = viewTypologiesService.getTypologyById(typologyId);

        // Assert

        assertEquals(typologyDTOOptional, typologyDTOOptionalResult);
    }

    @Test
    void shouldNotReturnTypologyById() {

        // Arrange

        String typologyId = "TY-01";

        TypologyID typologyIdVo = TypologyID.createTypologyIdWithString(typologyId);

        when(typologyRepository.findTypologyById(typologyIdVo)).thenReturn(Optional.empty());

        // Act

        Optional<TypologyDTO> typologyDTOOptionalResult = viewTypologiesService.getTypologyById(typologyId);

        // Assert

        assertEquals(Optional.empty(), typologyDTOOptionalResult);

    }
}