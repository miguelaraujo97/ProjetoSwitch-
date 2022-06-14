package switchisep.project.dto.assemblers;

import org.junit.jupiter.api.Test;
import switchisep.project.dto.TypologyDTO;
import switchisep.project.domain.typology.Typology;
import switchisep.project.domain.valueobjects.Description;
import switchisep.project.domain.valueobjects.TypologyID;

import static org.junit.jupiter.api.Assertions.*;

class TypologyDomainDTOAssemblerTest {

    @Test
    void toDTO(){

        TypologyDomainDTOAssembler typologyDomainDTOAssembler = new TypologyDomainDTOAssembler();

        TypologyID typologyID = TypologyID.createTypologyIdWithString("ID");
        Typology typology = new Typology(typologyID, Description.createDescription("Desc"));

        TypologyDTO typologyDTO = new TypologyDTO();
        typologyDTO.typologyId = "ID";
        typologyDTO.typologyDescription = "Desc";

        TypologyDTO result = typologyDomainDTOAssembler.toDTO(typology);

        assertEquals(typologyDTO, result);
    }

}