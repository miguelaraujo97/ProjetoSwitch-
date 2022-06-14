package switchisep.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import switchisep.project.dto.TypologyCreationDTO;
import switchisep.project.dto.TypologyDTO;
import switchisep.project.dto.assemblers.TypologyDomainDTOAssembler;
import switchisep.project.domain.typology.Typology;
import switchisep.project.domain.typology.TypologyFactoryInterface;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.repositories.TypologyRepository;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateTypologyServiceTest {

    @InjectMocks
    CreateTypologyService createTypologyService;

    @Mock
    TypologyRepository typologyRepository;

    @Mock
    TypologyFactoryInterface typologyFactoryInterface;

    @Mock
    TypologyDomainDTOAssembler typologyDomainDTOAssembler;

    @Test
    void createTypologyAndSave_success_typologyIsNew() {
        Description typologyDescription = Description.createDescription("TYPOLOGY A");

        TypologyCreationDTO typologyCreationDTO = new TypologyCreationDTO();

        typologyCreationDTO.description = "TYPOLOGY A";

        when(typologyRepository.existsByTypologyDescription(any())).thenReturn(false);

        Typology typology = mock(Typology.class);

        when(typologyFactoryInterface.createTypology(any(),any())).thenReturn(typology);

        when(typologyRepository.save(any())).thenReturn(Optional.ofNullable(typology));

        TypologyDTO expected = new TypologyDTO();

        when(typologyDomainDTOAssembler.toDTO(typology)).thenReturn(expected);

        expected.typologyDescription=typologyDescription.getDescription();


        Optional<TypologyDTO> result = createTypologyService.createTypologyAndSave(typologyCreationDTO);

        assertEquals(expected.typologyDescription, result.get().typologyDescription);
        verify(typologyRepository, times(1)).save(typology);
    }

    @Test
    void createTypologyAndSave_fail_typologyAlreadyExists() {

        Description typologyDescription = Description.createDescription("TYPOLOGY A");
        TypologyCreationDTO typologyCreationDTO = new TypologyCreationDTO();
        typologyCreationDTO.description = "TYPOLOGY A";
        when(typologyRepository.existsByTypologyDescription(any())).thenReturn(true);

        Typology typology = mock(Typology.class);

        TypologyDTO expected = new TypologyDTO();
        expected.typologyDescription=typologyDescription.getDescription();

        Optional<TypologyDTO> result = createTypologyService.createTypologyAndSave(typologyCreationDTO);

        assertEquals(Optional.empty(), result);

        verify(typologyRepository, times(0)).save(typology);

    }
}